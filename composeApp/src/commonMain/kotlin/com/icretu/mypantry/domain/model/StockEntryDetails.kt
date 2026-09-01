package com.icretu.mypantry.domain.model

import com.icretu.mypantry.presentation.pantry.StockEntryUiModel
import com.icretu.mypantry.utils.DateUtils
import kotlinx.datetime.LocalDate

data class StockEntryDetails(
    val id: String,
    val householdId: String,

    val productId: String,
    val productName: String,
    val productBrand: String?,

    val categoryId: String,
    val categoryName: String,

    val locationId: String,
    val locationName: String,

    val quantity: Int,
    val unit: String,

    val expirationDate: LocalDate? = null,
    val purchaseDate: LocalDate? = null,
    val storeName: String? = null,
    val price: Double? = null,
    val notes: String? = null,

    val updatedAtEpochMillis: Long,
    val updatedBy: String
)

fun StockEntryDetails.toUiModel(): StockEntryUiModel {
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

private fun buildName(entry: StockEntryDetails): String {
    return if (entry.productBrand.isNullOrBlank()) {
        entry.productName
    } else {
        "${entry.productName} (${entry.productBrand})"
    }
}
