package com.icretu.mypantry.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.icretu.mypantry.core.data.local.converters.LocalDateConverter
import com.icretu.mypantry.core.data.local.converters.SyncStatusConverter
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
    exportSchema = true
)
@TypeConverters(
    LocalDateConverter::class,
    SyncStatusConverter::class,
)
@ConstructedBy(PantryDatabaseConstructor::class)
abstract class PantryDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun stockEntryDao(): StockEntryDao
    abstract fun storageLocationDao(): StorageLocationDao
    abstract fun categoryDao(): CategoryDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PantryDatabaseConstructor : RoomDatabaseConstructor<PantryDatabase> {
    override fun initialize(): PantryDatabase
}
