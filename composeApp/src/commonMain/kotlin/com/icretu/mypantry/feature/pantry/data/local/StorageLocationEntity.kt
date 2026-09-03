package com.icretu.mypantry.feature.pantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.feature.pantry.domain.model.StorageLocation

@Entity(tableName = "storage_locations")
data class StorageLocationEntity(
    @PrimaryKey
    val id: String,
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
