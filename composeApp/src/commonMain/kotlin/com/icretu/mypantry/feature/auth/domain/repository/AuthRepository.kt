package com.icretu.mypantry.feature.auth.domain.repository

import com.icretu.mypantry.feature.auth.domain.model.AuthUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: Flow<AuthUser?>

    suspend fun signUp(
        email: String,
        password: String
    )

    suspend fun signIn(
        email: String,
        password: String
    )

    suspend fun signOut()
}
