package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class ObserveStockEntriesUseCase(
    private val repository: PantryRepository
) {
    operator fun invoke() = repository.observeStockEntries()
}
