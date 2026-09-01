package com.icretu.mypantry.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.icretu.mypantry.data.local.converters.LocalDateConverter
import com.icretu.mypantry.data.local.converters.SyncStatusConverter

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
