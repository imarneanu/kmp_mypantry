package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.PantryRepository

class DeletePantryItemUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteItem(id)
    }
}
