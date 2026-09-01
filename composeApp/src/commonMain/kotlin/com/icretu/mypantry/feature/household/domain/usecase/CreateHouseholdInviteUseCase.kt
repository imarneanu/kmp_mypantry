package com.icretu.mypantry.feature.household.domain.usecase

import com.icretu.mypantry.feature.household.domain.repository.HouseholdRepository
import com.icretu.mypantry.core.session.SessionRepository
import kotlinx.coroutines.flow.firstOrNull

class CreateHouseholdInviteUseCase(
    private val householdRepository: HouseholdRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(): String {
        val session = sessionRepository.session.firstOrNull()
            ?: error("No authenticated user")

        val householdId = session.householdId
            ?: error("No active household")

        return householdRepository.createInvite(
            householdId = householdId,
            userId = session.userId
        )
    }
}
