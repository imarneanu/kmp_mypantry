package com.icretu.mypantry.data.remote

import com.icretu.mypantry.domain.model.AuthUser
import com.icretu.mypantry.domain.repository.AuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseAuthRepository : AuthRepository {

    override val currentUser: Flow<AuthUser?> =
        Firebase.auth.authStateChanged.map { user ->
            user?.let {
                AuthUser(
                    uid = it.uid,
                    email = it.email
                )
            }
        }

    override suspend fun signUp(
        email: String,
        password: String
    ) {
        Firebase.auth.createUserWithEmailAndPassword(
            email = email,
            password = password
        )
    }

    override suspend fun signIn(
        email: String,
        password: String
    ) {
        Firebase.auth.signInWithEmailAndPassword(
            email = email,
            password = password
        )
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }
}
