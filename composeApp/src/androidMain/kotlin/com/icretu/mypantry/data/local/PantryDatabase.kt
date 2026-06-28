package com.icretu.mypantry.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PantryItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PantryDatabase : RoomDatabase() {
    abstract fun pantryItemDao(): PantryItemDao
}
