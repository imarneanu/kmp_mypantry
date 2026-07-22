package com.icretu.mypantry.data.local

import com.icretu.mypantry.domain.model.StockEntry
import kotlinx.datetime.LocalDate

data class StockEntryWithDetails(
    val id: Long,

    val productId: Long,
    val productName: String,
    val productBrand: String?,

    val categoryId: Long,
    val categoryName: String,

    val quantity: Int,
    val unit: String,

    val locationId: Long,
    val locationName: String,

    val expirationDate: LocalDate?,
    val purchaseDate: LocalDate?,
    val storeName: String?,
    val price: Double?,
    val notes: String?
)

fun StockEntryWithDetails.toDomain() =
    StockEntry(
        id = id,
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
        notes = notes
    )
