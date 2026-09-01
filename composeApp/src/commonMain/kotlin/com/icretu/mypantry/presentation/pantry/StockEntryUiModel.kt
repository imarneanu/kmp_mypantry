package com.icretu.mypantry.presentation.pantry

import com.icretu.mypantry.domain.model.ExpiryColor
import com.icretu.mypantry.domain.model.ExpiryStatus
import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.model.toExpiryStatus
import com.icretu.mypantry.utils.DateUtils
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
