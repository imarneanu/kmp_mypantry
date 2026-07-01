package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage_locations")
data class StorageLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String = "Other"
)
