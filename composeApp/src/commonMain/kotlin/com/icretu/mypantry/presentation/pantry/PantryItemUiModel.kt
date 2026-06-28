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
    val quantityText: String,
    val location: String,
    val category: String,
    val expirationDate: LocalDate? = null,
    val expirationText: String,
    val expirationColor: ExpiryColor,
)

fun PantryItem.toUiModel(): PantryItemUiModel {
    val status = this.expirationDate.toExpiryStatus()

    return PantryItemUiModel(
        id = this.id,
        name = this.name,
        quantityText = "${this.quantity} ${this.unit}",
        location = this.location,
        category = this.category,
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
    )
}
