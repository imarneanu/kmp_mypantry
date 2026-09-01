package com.icretu.mypantry.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.icretu.mypantry.data.local.converters.LocalDateConverter
import com.icretu.mypantry.data.local.converters.SyncStatusConverter
import com.icretu.mypantry.feature.pantry.data.local.CategoryDao
import com.icretu.mypantry.feature.pantry.data.local.CategoryEntity
import com.icretu.mypantry.feature.pantry.data.local.ProductDao
import com.icretu.mypantry.feature.pantry.data.local.ProductEntity
import com.icretu.mypantry.feature.pantry.data.local.StockEntryDao
import com.icretu.mypantry.feature.pantry.data.local.StockEntryEntity
import com.icretu.mypantry.feature.pantry.data.local.StorageLocationDao
import com.icretu.mypantry.feature.pantry.data.local.StorageLocationEntity

@Database(
    entities = [
        ProductEntity::class,
        StockEntryEntity::class,
        StorageLocationEntity::class,
        CategoryEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LocalDateConverter::class,
    SyncStatusConverter::class,
)
abstract class PantryDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun stockEntryDao(): StockEntryDao
    abstract fun storageLocationDao(): StorageLocationDao
    abstract fun categoryDao(): CategoryDao
}
