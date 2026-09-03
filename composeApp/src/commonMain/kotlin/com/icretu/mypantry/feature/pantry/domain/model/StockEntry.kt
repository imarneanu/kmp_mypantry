package com.icretu.mypantry.feature.pantry.domain.model

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

    val updatedAtEpochMillis: Long = 0,
    val updatedBy: String,

    val isDeleted: Boolean = false,
)
