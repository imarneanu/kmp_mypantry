package com.icretu.mypantry.data.remote.model

import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.model.StockEntryRecord
import com.icretu.mypantry.domain.sync.SyncStatus
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class RemoteStockEntry(
    val id: String = "",
    val householdId: String = "",

    val productId: String = "",
    val locationId: String = "",

    val quantity: Int = 0,
    val unit: String = "",

    val expirationDate: String? = null,
    val purchaseDate: String? = null,
    val storeName: String? = null,
    val price: Double? = null,
    val notes: String? = null,

    val updatedAtEpochMillis: Long = 0,
    val updatedBy: String = "",

    val isDeleted: Boolean = false,
)

fun RemoteStockEntry.toDomain(): StockEntry =
    StockEntry(
        id = id,
        householdId = householdId,
        productId = productId,
        productName = "",
        productBrand = null,
        categoryId = "",
        categoryName = "",
        quantity = quantity,
        unit = unit,
        locationId = locationId,
        locationName = "",
        expirationDate = expirationDate?.let(LocalDate::parse),
        purchaseDate = purchaseDate?.let(LocalDate::parse),
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
    )

fun RemoteStockEntry.toRecord(): StockEntryRecord =
    StockEntryRecord(
        id = id,
        householdId = householdId,
        productId = productId,
        locationId = locationId,
        quantity = quantity,
        unit = unit,
        expirationDate = expirationDate?.let(LocalDate::parse),
        purchaseDate = purchaseDate?.let(LocalDate::parse),
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,

        // A record coming FROM Firebase is already synchronized.
        syncStatus = SyncStatus.SYNCED,

        isDeleted = isDeleted,
    )
