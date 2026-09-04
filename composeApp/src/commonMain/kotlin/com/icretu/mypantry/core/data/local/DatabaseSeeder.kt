package com.icretu.mypantry.core.data.local

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
                    StorageLocationEntity(DefaultStorageLocationIds.PANTRY, name = "Pantry", type = "Pantry"),
                    StorageLocationEntity(DefaultStorageLocationIds.FREEZER, name = "Freezer", type = "Freezer"),
                    StorageLocationEntity(DefaultStorageLocationIds.CUPBOARD, name = "Cupboard", type = "Cupboard"),
                    StorageLocationEntity(DefaultStorageLocationIds.BATHROOM, name = "Bathroom", type = "Bathroom")
                )
            )
        }

        if (categoryDao.count() == 0) {
            categoryDao.insertAll(
                listOf(
                    CategoryEntity(DefaultCategoryIds.ESSENTIALS, name = "Essentials"),
                    CategoryEntity(DefaultCategoryIds.FROZEN_FOOD, name = "Frozen food"),
                    CategoryEntity(DefaultCategoryIds.TEA_COFFEE, name = "Tea & coffee"),
                    CategoryEntity(DefaultCategoryIds.TOILETRIES, name = "Toiletries"),
                    CategoryEntity(DefaultCategoryIds.CLEANING_PRODUCTS, name = "Cleaning products")
                )
            )
        }
    }
}

object DefaultCategoryIds {
    const val ESSENTIALS = "category_essentials"
    const val FROZEN_FOOD = "category_frozen_food"
    const val TEA_COFFEE = "category_tea_coffee"
    const val TOILETRIES = "category_toiletries"
    const val CLEANING_PRODUCTS = "category_cleaning_products"
}

object DefaultStorageLocationIds {
    const val PANTRY = "location_pantry"
    const val FREEZER = "location_freezer"
    const val CUPBOARD = "location_cupboard"
    const val BATHROOM = "location_bathroom"
}
