package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.domain.model.Category

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
