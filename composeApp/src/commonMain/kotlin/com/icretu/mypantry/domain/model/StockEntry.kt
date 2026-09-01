package com.icretu.mypantry.domain.model

import com.icretu.mypantry.data.remote.model.RemoteStockEntry
import com.icretu.mypantry.domain.sync.SyncStatus
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

    val syncStatus: SyncStatus = SyncStatus.PENDING,
    val isDeleted: Boolean = false,
)

fun StockEntry.toRemote(): RemoteStockEntry =
    RemoteStockEntry(
        id = id,
        householdId = householdId,
        productId = productId,
        quantity = quantity,
        unit = unit,
        locationId = locationId,
        expirationDate = expirationDate?.toString(),
        purchaseDate = purchaseDate?.toString(),
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
    )
