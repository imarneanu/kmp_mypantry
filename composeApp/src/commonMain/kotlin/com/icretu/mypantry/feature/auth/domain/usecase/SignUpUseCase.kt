package com.icretu.mypantry.feature.auth.domain.usecase

import com.icretu.mypantry.feature.auth.domain.repository.AuthRepository

class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) {
        authRepository.signUp(email, password)
    }
}
