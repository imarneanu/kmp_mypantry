package com.icretu.mypantry.feature.auth.domain.usecase

import com.icretu.mypantry.feature.auth.domain.repository.AuthRepository

class SignInUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) {
        authRepository.signIn(email, password)
    }
}
