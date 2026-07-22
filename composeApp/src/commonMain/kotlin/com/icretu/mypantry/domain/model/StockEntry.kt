package com.icretu.mypantry.domain.model

import kotlinx.datetime.LocalDate

data class StockEntry(
    val id: Long = 0,

    val productId: Long,
    val productName: String,
    val productBrand: String? = null,

    val categoryId: Long,
    val categoryName: String,

    val quantity: Int,
    val unit: String,

    val locationId: Long,
    val locationName: String,

    val expirationDate: LocalDate? = null,
    val purchaseDate: LocalDate? = null,
    val storeName: String? = null,
    val price: Double? = null,
    val notes: String? = null
)
