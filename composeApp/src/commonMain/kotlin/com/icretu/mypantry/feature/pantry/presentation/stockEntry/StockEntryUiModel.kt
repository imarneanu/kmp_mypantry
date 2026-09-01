package com.icretu.mypantry.feature.pantry.presentation.stockEntry

import com.icretu.mypantry.feature.pantry.domain.model.ExpiryColor
import kotlinx.datetime.LocalDate

data class StockEntryUiModel(
    val id: String,
    val productId: String,
    val productName: String,
    val productBrand: String?,
    val quantity: Int,
    val unit: String,
    val quantityText: String,
    val locationId: String,
    val locationName: String,
    val categoryId: String,
    val categoryName: String,
    val expirationDate: LocalDate?,
    val expirationText: String,
    val expirationColor: ExpiryColor,
    val storeName: String?,
    val price: String?,
    val notes: String?,
)
