package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.domain.model.StorageLocation

@Entity(tableName = "storage_locations")
data class StorageLocationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val type: String = "Other"
)


fun StorageLocationEntity.toDomain(): StorageLocation {
    return StorageLocation(
        id = id,
        name = name,
        type = type
    )
}
