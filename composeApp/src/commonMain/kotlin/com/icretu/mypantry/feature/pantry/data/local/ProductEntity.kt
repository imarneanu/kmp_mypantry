package com.icretu.mypantry.feature.pantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.core.sync.SyncStatus
import com.icretu.mypantry.feature.pantry.domain.model.Product
import com.icretu.mypantry.feature.pantry.domain.model.StockEntry

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val householdId: String,
    val categoryId: String,

    val name: String,
    val brand: String? = null,
    val defaultUnit: String = "pcs",

    val barcode: String? = null,
    val notes: String? = null,

    val updatedAtEpochMillis: Long,
    val updatedBy: String,

    val syncStatus: SyncStatus = SyncStatus.PENDING,
)

fun ProductEntity.toDomain() =
    Product(
        id = id,
        householdId = householdId,
        name = name,
        brand = brand,
        categoryId = categoryId,
        defaultUnit = defaultUnit,
        barcode = barcode,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
    )

fun Product.toSyncedEntity() = toEntity(SyncStatus.SYNCED)

fun Product.toEntity(syncStatus: SyncStatus = SyncStatus.PENDING) =
    ProductEntity(
        id = id,
        householdId = householdId,
        name = name,
        brand = brand,
        categoryId = categoryId,
        defaultUnit = defaultUnit,
        barcode = barcode,
        notes = notes,
        updatedAtEpochMillis = updatedAtEpochMillis,
        updatedBy = updatedBy,
        syncStatus = syncStatus,
    )
