package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.model.StockEntryRecord
import com.icretu.mypantry.domain.sync.SyncStatus
import kotlinx.datetime.LocalDate

@Entity(
    tableName = "stock_entries",
    indices = [
        Index("householdId"),
        Index("productId"),
        Index("locationId")
    ],
)
data class StockEntryEntity(
    @PrimaryKey
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

fun StockEntry.toEntity() =
    StockEntryEntity(
        id = id,
        householdId = householdId,
        productId = productId,
        quantity = quantity,
        unit = unit,
        locationId = locationId,
        expirationDate = expirationDate,
        purchaseDate = purchaseDate,
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
        syncStatus = SyncStatus.PENDING,
        isDeleted = false,
    )

fun StockEntryEntity.toRecord(): StockEntryRecord =
    StockEntryRecord(
        id = id,
        householdId = householdId,
        productId = productId,
        locationId = locationId,
        quantity = quantity,
        unit = unit,
        expirationDate = expirationDate,
        purchaseDate = purchaseDate,
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
        syncStatus = syncStatus,
        isDeleted = isDeleted,
    )

fun StockEntryRecord.toEntity(): StockEntryEntity =
    StockEntryEntity(
        id = id,
        householdId = householdId,
        productId = productId,
        locationId = locationId,
        quantity = quantity,
        unit = unit,
        expirationDate = expirationDate,
        purchaseDate = purchaseDate,
        storeName = storeName,
        price = price,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
        syncStatus = syncStatus,
        isDeleted = isDeleted,
    )
