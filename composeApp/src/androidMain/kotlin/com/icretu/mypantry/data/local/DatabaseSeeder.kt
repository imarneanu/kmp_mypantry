package com.icretu.mypantry.data.local

import com.icretu.mypantry.core.utils.IdGenerator
import com.icretu.mypantry.feature.pantry.data.local.CategoryDao
import com.icretu.mypantry.feature.pantry.data.local.CategoryEntity
import com.icretu.mypantry.feature.pantry.data.local.StorageLocationDao
import com.icretu.mypantry.feature.pantry.data.local.StorageLocationEntity

class DatabaseSeeder(
    private val storageLocationDao: StorageLocationDao,
    private val categoryDao: CategoryDao,
    private val idGenerator: IdGenerator,
) {
    suspend fun seedIfNeeded() {
        if (storageLocationDao.count() == 0) {
            storageLocationDao.insertAll(
                listOf(
                    StorageLocationEntity(id = idGenerator.generate(), name = "Pantry", type = "Pantry"),
                    StorageLocationEntity(id = idGenerator.generate(), name = "Freezer", type = "Freezer"),
                    StorageLocationEntity(id = idGenerator.generate(), name = "Cupboard", type = "Cupboard"),
                    StorageLocationEntity(id = idGenerator.generate(), name = "Bathroom", type = "Bathroom")
                )
            )
        }

        if (categoryDao.count() == 0) {
            categoryDao.insertAll(
                listOf(
                    CategoryEntity(id = idGenerator.generate(), name = "Essentials"),
                    CategoryEntity(id = idGenerator.generate(), name = "Frozen food"),
                    CategoryEntity(id = idGenerator.generate(), name = "Tea & coffee"),
                    CategoryEntity(id = idGenerator.generate(), name = "Toiletries"),
                    CategoryEntity(id = idGenerator.generate(), name = "Cleaning products")
                )
            )
        }
    }
}
