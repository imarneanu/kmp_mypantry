package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.repository.PantryRepository

class UpsertStockEntryUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(entry: StockEntry) {
        repository.upsertStockEntry(entry)
    }
}
