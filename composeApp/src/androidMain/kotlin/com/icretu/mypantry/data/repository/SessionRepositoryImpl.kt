package com.icretu.mypantry.data.repository

import com.icretu.mypantry.domain.model.UserSession
import com.icretu.mypantry.domain.repository.AuthRepository
import com.icretu.mypantry.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionRepositoryImpl(
    authRepository: AuthRepository
) : SessionRepository {

    override val session: Flow<UserSession?> =
        authRepository.currentUser.map { user ->
            user?.let {
                UserSession(
                    userId = it.uid,
                    email = it.email,
                    householdId = null,
                )
            }
        }
}
