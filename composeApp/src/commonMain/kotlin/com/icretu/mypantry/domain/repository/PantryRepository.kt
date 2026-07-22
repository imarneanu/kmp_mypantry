package com.icretu.mypantry.domain.repository

import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.Product
import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.model.StorageLocation
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    fun observeStockEntries(): Flow<List<StockEntry>>
    fun observeProducts(): Flow<List<Product>>
    fun observeLocations(): Flow<List<StorageLocation>>
    fun observeCategories(): Flow<List<Category>>

    suspend fun upsertProduct(product: Product): Long
    suspend fun upsertStockEntry(entry: StockEntry)
    suspend fun deleteStockEntry(id: Long)
}
