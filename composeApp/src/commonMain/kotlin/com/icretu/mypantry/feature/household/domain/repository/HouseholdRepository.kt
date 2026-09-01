package com.icretu.mypantry.feature.household.domain.repository

import kotlinx.coroutines.flow.Flow

interface HouseholdRepository {
    suspend fun createHousehold(
        name: String,
        userId: String
    ): String

    suspend fun joinHousehold(
        inviteCode: String,
        userId: String
    ): String

    fun observeHouseholdIdForUser(
        userId: String
    ): Flow<String?>

    suspend fun createInvite(
        householdId: String,
        userId: String
    ): String
}
