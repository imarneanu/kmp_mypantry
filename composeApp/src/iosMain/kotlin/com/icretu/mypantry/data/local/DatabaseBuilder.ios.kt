package com.icretu.mypantry.data.local

import androidx.room.Room
import androidx.room.RoomDatabase
import com.icretu.mypantry.core.data.local.PantryDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
fun createIosDatabaseBuilder(): RoomDatabase.Builder<PantryDatabase> {
    val documentDirectory =
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = true,
            error = null,
        )

    val dbFilePath =
        requireNotNull(documentDirectory)
            .URLByAppendingPathComponent("pantry.db")
            ?.path
            ?: error("Unable to create database path")

    return Room.databaseBuilder<PantryDatabase>(
        name = dbFilePath,
    )
}
