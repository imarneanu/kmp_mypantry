package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.AuthRepository

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
