package com.icretu.mypantry.domain.model

data class PantryItem(
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
