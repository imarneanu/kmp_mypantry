package com.icretu.mypantry.data.local

import com.icretu.mypantry.domain.model.StockEntry
import kotlinx.datetime.LocalDate

data class StockEntryWithDetails(
    val id: String,
    val householdId: String,

    val productId: String,
    val productName: String,
    val productBrand: String?,

    val categoryId: String,
    val categoryName: String,

    val quantity: Int,
    val unit: String,

    val locationId: String,
    val locationName: String,

    val expirationDate: LocalDate?,
    val purchaseDate: LocalDate?,
    val storeName: String?,
    val price: Double?,
    val notes: String?,

    val updatedAtEpochMillis: Long,
    val updatedBy: String,
)

fun StockEntryWithDetails.toDomain() =
    StockEntry(
        id = id,
        householdId = householdId,
        productId = productId,
        productName = productName,
        productBrand = productBrand,
        categoryId = categoryId,
        categoryName = categoryName,
        quantity = quantity,
        unit = unit,
        locationId = locationId,
        locationName = locationName,
        expirationDate = expirationDate,
        purchaseDate = purchaseDate,
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
    )
