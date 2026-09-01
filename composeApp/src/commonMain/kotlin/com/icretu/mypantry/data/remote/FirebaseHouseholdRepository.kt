package com.icretu.mypantry.data.remote

import com.icretu.mypantry.domain.model.HouseholdRole
import com.icretu.mypantry.domain.repository.HouseholdRepository
import com.icretu.mypantry.domain.util.IdGenerator
import com.icretu.mypantry.domain.util.TimestampProvider
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseHouseholdRepository(
    private val idGenerator: IdGenerator,
    private val timestampProvider: TimestampProvider
) : HouseholdRepository {

    private val firestore
        get() = Firebase.firestore

    override suspend fun createHousehold(
        name: String,
        userId: String
    ): String {
        val householdId = idGenerator.generate()
        val now = timestampProvider.nowEpochMillis()

        val householdRef = firestore
            .collection("households")
            .document(householdId)

        householdRef.set(
            mapOf(
                "id" to householdId,
                "name" to name,
                "createdBy" to userId,
                "createdAtEpochMillis" to now
            )
        )

        householdRef
            .collection("members")
            .document(userId)
            .set(
                mapOf(
                    "userId" to userId,
                    "role" to HouseholdRole.OWNER.name,
                    "joinedAtEpochMillis" to now
                )
            )

        firestore
            .collection("users")
            .document(userId)
            .set(
                mapOf(
                    "activeHouseholdId" to householdId
                ),
                merge = true
            )

        return householdId
    }

    override suspend fun joinHousehold(
        inviteCode: String,
        userId: String
    ): String {
        val invite = firestore
            .collection("householdInvites")
            .document(inviteCode)
            .get()

        val householdId =
            invite.get<String?>("householdId")
                ?: error("Invalid household invite")

        val usedBy = invite.get<String?>("usedBy")
        if (usedBy != null) {
            error("Invite code has already been used")
        }

        val now = timestampProvider.nowEpochMillis()

        firestore
            .collection("households")
            .document(householdId)
            .collection("members")
            .document(userId)
            .set(
                mapOf(
                    "userId" to userId,
                    "role" to HouseholdRole.MEMBER.name,
                    "joinedAtEpochMillis" to now
                )
            )

        firestore
            .collection("users")
            .document(userId)
            .set(
                mapOf(
                    "activeHouseholdId" to householdId
                ),
                merge = true
            )

        firestore
            .collection("householdInvites")
            .document(inviteCode)
            .update(
                mapOf(
                    "usedBy" to userId
                )
            )

        return householdId
    }

    override fun observeHouseholdIdForUser(
        userId: String
    ): Flow<String?> =
        firestore
            .collection("users")
            .document(userId)
            .snapshots
            .map { document ->
                document.get<String?>("activeHouseholdId")
            }

    override suspend fun createInvite(
        householdId: String,
        userId: String
    ): String {
        val inviteCode = idGenerator
            .generate()
            .replace("-", "")
            .take(8)
            .uppercase()

        val now = timestampProvider.nowEpochMillis()

        firestore
            .collection("householdInvites")
            .document(inviteCode)
            .set(
                mapOf(
                    "householdId" to householdId,
                    "createdBy" to userId,
                    "createdAtEpochMillis" to now,
                    "usedBy" to null
                )
            )

        return inviteCode
    }
}
