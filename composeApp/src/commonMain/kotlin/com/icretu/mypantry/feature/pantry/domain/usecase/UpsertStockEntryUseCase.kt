package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.core.time.TimestampProvider
import com.icretu.mypantry.feature.pantry.domain.model.StockEntry
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class UpsertStockEntryUseCase(
    private val repository: PantryRepository,
    private val timestampProvider: TimestampProvider,
) {
    suspend operator fun invoke(entry: StockEntry) {
        repository.upsertStockEntry(
            entry.copy(
                purchaseDate = entry.purchaseDate ?: timestampProvider.nowLocalDate(),
                updatedAtEpochMillis = timestampProvider.nowEpochMillis(),
            )
        )
    }
}
