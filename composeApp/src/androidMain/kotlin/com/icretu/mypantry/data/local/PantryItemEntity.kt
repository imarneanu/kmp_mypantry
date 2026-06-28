package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pantry_items")
data class PantryItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val quantity: Int,
    val unit: String,
    val location: String,
    val category: String,
    val expirationDateMillis: Long? = null,
    val storeName: String? = null,
    val price: Double? = null,
    val notes: String? = null
)
