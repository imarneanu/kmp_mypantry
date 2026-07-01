package com.icretu.mypantry.data.local

import kotlinx.datetime.LocalDate

data class PantryItemWithDetails(
    val id: Long,
    val name: String,
    val quantity: Int,
    val unit: String,
    val locationId: Long,
    val locationName: String,
    val categoryId: Long,
    val categoryName: String,
    val expirationDate: LocalDate?,
    val storeName: String?,
    val price: Double?,
    val notes: String?
)
