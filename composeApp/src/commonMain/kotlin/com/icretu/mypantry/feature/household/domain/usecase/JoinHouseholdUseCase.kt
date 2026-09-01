package com.icretu.mypantry.feature.household.domain.usecase

import com.icretu.mypantry.feature.household.domain.repository.HouseholdRepository
import com.icretu.mypantry.core.session.SessionRepository
import kotlinx.coroutines.flow.firstOrNull

class JoinHouseholdUseCase(
    private val householdRepository: HouseholdRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(inviteCode: String): String {
        val session = sessionRepository.session.firstOrNull()
            ?: error("No authenticated user")

        return householdRepository.joinHousehold(
            inviteCode = inviteCode,
            userId = session.userId
        )
    }
}
