package com.icretu.mypantry.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        PantryItemEntity::class,
        StorageLocationEntity::class,
        CategoryEntity::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class PantryDatabase : RoomDatabase() {
    abstract fun pantryItemDao(): PantryItemDao
    abstract fun storageLocationDao(): StorageLocationDao
    abstract fun categoryDao(): CategoryDao
}
