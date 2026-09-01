package com.icretu.mypantry.feature.pantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.feature.pantry.domain.model.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String
)

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name
    )
}
