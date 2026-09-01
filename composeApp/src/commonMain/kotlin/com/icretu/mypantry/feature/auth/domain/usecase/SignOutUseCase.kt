package com.icretu.mypantry.feature.auth.domain.usecase

import com.icretu.mypantry.feature.auth.domain.repository.AuthRepository

class SignOutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.signOut()
    }
}
