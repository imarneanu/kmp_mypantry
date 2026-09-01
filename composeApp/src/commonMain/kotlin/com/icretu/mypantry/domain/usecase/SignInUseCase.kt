package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.AuthRepository

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
