package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.PantryRepository

class ObservePantryItemsUseCase(
    private val repository: PantryRepository
) {
    operator fun invoke() = repository.observeItems()
}
