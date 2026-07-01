package com.icretu.mypantry.presentation.pantry

import com.icretu.mypantry.domain.model.ExpiryColor
import com.icretu.mypantry.domain.model.ExpiryStatus
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.model.toExpiryStatus
import com.icretu.mypantry.utils.DateUtils
import kotlinx.datetime.LocalDate

data class PantryItemUiModel(
    val id: Long,
    val name: String,
    val quantity: String,
    val unit: String,
    val locationId: Long,
    val locationName: String,
    val categoryId: Long,
    val categoryName: String,
    val expirationDate: LocalDate? = null,
    val expirationText: String,
    val expirationColor: ExpiryColor,
    val storeName: String?,
    val price: String?,
    val notes: String?,
) {
    val quantityText = "${this.quantity} ${this.unit}"
}

fun PantryItem.toUiModel(): PantryItemUiModel {
    val status = this.expirationDate.toExpiryStatus()

    return PantryItemUiModel(
        id = this.id,
        name = this.name,
        quantity = this.quantity.toString(),
        unit = this.unit,
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
        price = this.price.toString(),
        notes = this.notes,
    )
}
