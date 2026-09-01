package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.feature.pantry.domain.model.StockEntry
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class UpsertStockEntryUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(entry: StockEntry) {
        repository.upsertStockEntry(entry)
    }
}
