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

fun StockEntry.toUiModel(): StockEntryUiModel {
    val status = this.expirationDate.toExpiryStatus()

    return StockEntryUiModel(
        id = this.id,
        productId = this.productId,
        productName = buildName(this),
        productBrand = this.productBrand,
        quantity = this.quantity,
        unit = this.unit,
        quantityText = "${this.quantity} ${this.unit}",
        locationId = this.locationId,
        locationName = this.locationName,
        categoryId = this.categoryId,
        categoryName = this.categoryName,
        expirationDate = this.expirationDate,
        expirationText = when (status) {
            ExpiryStatus.NONE -> "No expiration date"
            ExpiryStatus.OK -> "Expires: ${DateUtils.formatDate(this.expirationDate)}"
            ExpiryStatus.EXPIRING_SOON -> "Expires soon: ${DateUtils.formatDate(this.expirationDate)}"
            ExpiryStatus.EXPIRED -> "Expired: ${DateUtils.formatDate(this.expirationDate)}"
        },
        expirationColor = when (status) {
            ExpiryStatus.NONE -> ExpiryColor.DEFAULT
            ExpiryStatus.OK -> ExpiryColor.DEFAULT
            ExpiryStatus.EXPIRING_SOON -> ExpiryColor.WARNING
            ExpiryStatus.EXPIRED -> ExpiryColor.ERROR
        },
        storeName = this.storeName,
        price = "${this.price}",
        notes = this.notes,
    )
}

private fun buildName(entry: StockEntry): String {
    return if (entry.productBrand.isNullOrBlank()) {
        entry.productName
    } else {
        "${entry.productName} (${entry.productBrand})"
    }
}
