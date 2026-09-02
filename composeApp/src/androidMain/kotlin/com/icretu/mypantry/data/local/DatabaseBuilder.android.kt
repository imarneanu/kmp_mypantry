package com.icretu.mypantry.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.icretu.mypantry.core.data.local.PantryDatabase

fun createAndroidDatabaseBuilder(
    context: Context,
): RoomDatabase.Builder<PantryDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("pantry.db")

    return Room.databaseBuilder<PantryDatabase>(
        context = appContext,
        name = dbFile.absolutePath,
        )
}
