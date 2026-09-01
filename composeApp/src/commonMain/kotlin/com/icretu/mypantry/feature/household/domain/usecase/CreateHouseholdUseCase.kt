package com.icretu.mypantry.feature.household.domain.usecase

import com.icretu.mypantry.feature.household.domain.repository.HouseholdRepository
import com.icretu.mypantry.core.session.SessionRepository
import kotlinx.coroutines.flow.firstOrNull

class CreateHouseholdUseCase(
    private val householdRepository: HouseholdRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(name: String): String {
        val session = sessionRepository.session.firstOrNull()
            ?: error("No authenticated user")

        return householdRepository.createHousehold(
            name = name,
            userId = session.userId
        )
    }
}
