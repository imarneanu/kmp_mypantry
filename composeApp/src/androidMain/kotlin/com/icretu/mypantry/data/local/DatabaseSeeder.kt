package com.icretu.mypantry.data.local

class DatabaseSeeder(
    private val storageLocationDao: StorageLocationDao,
    private val categoryDao: CategoryDao
) {
    suspend fun seedIfNeeded() {
        if (storageLocationDao.count() == 0) {
            storageLocationDao.insertAll(
                listOf(
                    StorageLocationEntity(name = "Pantry", type = "Pantry"),
                    StorageLocationEntity(name = "Freezer", type = "Freezer"),
                    StorageLocationEntity(name = "Cupboard", type = "Cupboard"),
                    StorageLocationEntity(name = "Bathroom", type = "Bathroom")
                )
            )
        }

        if (categoryDao.count() == 0) {
            categoryDao.insertAll(
                listOf(
                    CategoryEntity(name = "Essentials"),
                    CategoryEntity(name = "Frozen food"),
                    CategoryEntity(name = "Tea & coffee"),
                    CategoryEntity(name = "Toiletries"),
                    CategoryEntity(name = "Cleaning products")
                )
            )
        }
    }
}
