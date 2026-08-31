package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.PantryRepository

class DeleteStockEntryUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(id: String) = repository.deleteStockEntry(id)
}
