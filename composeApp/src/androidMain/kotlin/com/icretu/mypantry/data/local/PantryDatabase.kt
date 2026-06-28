package com.icretu.mypantry.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PantryItemEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class PantryDatabase : RoomDatabase() {
    abstract fun pantryItemDao(): PantryItemDao
}
