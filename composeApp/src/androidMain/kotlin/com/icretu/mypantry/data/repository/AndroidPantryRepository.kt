package com.icretu.mypantry.data.repository

import com.icretu.mypantry.data.local.CategoryDao
import com.icretu.mypantry.data.local.ProductDao
import com.icretu.mypantry.data.local.StockEntryDao
import com.icretu.mypantry.data.local.StorageLocationDao
import com.icretu.mypantry.data.local.toDomain
import com.icretu.mypantry.data.local.toEntity
import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.Product
import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.model.StockEntryDetails
import com.icretu.mypantry.domain.model.StorageLocation
import com.icretu.mypantry.domain.repository.PantryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidPantryRepository(
    private val stockEntryDao: StockEntryDao,
    private val productDao: ProductDao,
    private val storageLocationDao: StorageLocationDao,
    private val categoryDao: CategoryDao,
) : PantryRepository {

    override fun observeStockEntries(): Flow<List<StockEntryDetails>> =
        stockEntryDao.observeStockEntries()

    override fun observeProducts(): Flow<List<Product>> =
        productDao.observeProducts()
            .map { products -> products.map { it.toDomain() } }

    override fun observeLocations(): Flow<List<StorageLocation>> = storageLocationDao.observeLocations()
        .map { locations -> locations.map { it.toDomain() } }

    override fun observeCategories(): Flow<List<Category>> = categoryDao.observeCategories()
        .map { categories -> categories.map { it.toDomain() } }

    override suspend fun upsertProduct(product: Product): String {
        productDao.upsert(product.toEntity())
        return product.id
    }

    override suspend fun upsertStockEntry(entry: StockEntry) {
        stockEntryDao.upsert(entry.toEntity())
    }

    override suspend fun deleteStockEntry(
        id: String,
        updatedAtEpochMillis: Long,
        updatedBy: String
    ) {
        stockEntryDao.markDeleted(
            id = id,
            updatedAtEpochMillis = updatedAtEpochMillis,
            updatedBy = updatedBy
        )
    }
}
