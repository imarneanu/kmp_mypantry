package com.icretu.mypantry.data.repository

import com.icretu.mypantry.domain.model.UserSession
import com.icretu.mypantry.domain.repository.AuthRepository
import com.icretu.mypantry.domain.repository.HouseholdRepository
import com.icretu.mypantry.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SessionRepositoryImpl(
    authRepository: AuthRepository,
    private val householdRepository: HouseholdRepository,
) : SessionRepository {

    override val session: Flow<UserSession?> =
        authRepository.currentUser
            .flatMapLatest { user ->
                if (user == null) {
                    flowOf(null)
                } else {
                    householdRepository
                        .observeHouseholdIdForUser(user.uid)
                        .map { householdId ->
                            UserSession(
                                userId = user.uid,
                                email = user.email,
                                householdId = householdId
                            )
                        }
                }
            }
}
