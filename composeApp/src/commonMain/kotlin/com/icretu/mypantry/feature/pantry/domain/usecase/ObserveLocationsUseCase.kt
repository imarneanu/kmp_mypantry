package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class ObserveLocationsUseCase(
    private val repository: PantryRepository
) {
    operator fun invoke() = repository.observeLocations()
}
