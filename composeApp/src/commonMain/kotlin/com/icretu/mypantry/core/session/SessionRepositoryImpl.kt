package com.icretu.mypantry.core.session

import com.icretu.mypantry.feature.auth.domain.repository.AuthRepository
import com.icretu.mypantry.feature.household.domain.repository.HouseholdRepository
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
