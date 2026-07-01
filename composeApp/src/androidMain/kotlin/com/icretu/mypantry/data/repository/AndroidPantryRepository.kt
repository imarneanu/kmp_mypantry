package com.icretu.mypantry.data.repository

import com.icretu.mypantry.data.local.CategoryDao
import com.icretu.mypantry.data.local.PantryItemDao
import com.icretu.mypantry.data.local.StorageLocationDao
import com.icretu.mypantry.data.local.toDomain
import com.icretu.mypantry.data.local.toEntity
import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.model.StorageLocation
import com.icretu.mypantry.domain.repository.PantryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidPantryRepository(
    private val pantryItemDao: PantryItemDao,
    private val storageLocationDao: StorageLocationDao,
    private val categoryDao: CategoryDao,
) : PantryRepository {

    override fun observeItems(): Flow<List<PantryItem>> = pantryItemDao.observeItems()
        .map { entities -> entities.map { it.toDomain() } }

    override fun observeLocations(): Flow<List<StorageLocation>> = storageLocationDao.observeLocations()
        .map { locations -> locations.map { it.toDomain() } }

    override fun observeCategories(): Flow<List<Category>> = categoryDao.observeCategories()
        .map { categories -> categories.map { it.toDomain() } }

    override suspend fun addItem(item: PantryItem) {
        pantryItemDao.upsertItem(item.toEntity())
    }

    override suspend fun deleteItem(id: Long) {
        pantryItemDao.deleteById(id)
    }
}
