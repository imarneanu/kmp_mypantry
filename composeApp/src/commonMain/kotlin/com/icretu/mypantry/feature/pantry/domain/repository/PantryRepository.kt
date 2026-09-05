package com.icretu.mypantry.feature.pantry.domain.repository

import com.icretu.mypantry.feature.pantry.domain.model.Category
import com.icretu.mypantry.feature.pantry.domain.model.Product
import com.icretu.mypantry.feature.pantry.domain.model.StockEntry
import com.icretu.mypantry.feature.pantry.domain.model.StockEntryDetails
import com.icretu.mypantry.feature.pantry.domain.model.StorageLocation
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    fun observeStockEntries(householdId: String): Flow<List<StockEntryDetails>>
    fun observeProducts(householdId: String): Flow<List<Product>>
    fun observeLocations(): Flow<List<StorageLocation>>
    fun observeCategories(): Flow<List<Category>>

    suspend fun upsertProduct(product: Product): String

    suspend fun upsertStockEntry(entry: StockEntry)
    suspend fun deleteStockEntry(id: String, updatedAtEpochMillis: Long, updatedBy: String)
}
