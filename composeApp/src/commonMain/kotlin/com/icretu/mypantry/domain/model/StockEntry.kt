package com.icretu.mypantry.domain.model

import kotlinx.datetime.LocalDate

data class StockEntry(
    val id: String,
    val householdId: String,

    val productId: String,
    val locationId: String,

    val quantity: Int,
    val unit: String,

    val expirationDate: LocalDate? = null,
    val purchaseDate: LocalDate? = null,
    val storeName: String? = null,
    val price: Double? = null,
    val notes: String? = null,

    val updatedAtEpochMillis: Long,
    val updatedBy: String,

    val isDeleted: Boolean = false,
)
