package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class ObserveProductsUseCase(
    private val repository: PantryRepository
) {
    operator fun invoke(householdId: String) = repository.observeProducts(householdId)
}
