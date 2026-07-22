package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.domain.model.StockEntry
import kotlinx.datetime.LocalDate

@Entity(tableName = "stock_entries")
data class StockEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val productId: Long,
    val quantity: Int,
    val unit: String,

    val locationId: Long,

    val expirationDate: LocalDate? = null,
    val purchaseDate: LocalDate? = null,
    val storeName: String? = null,
    val price: Double? = null,
    val notes: String? = null
)

fun StockEntry.toEntity() =
    StockEntryEntity(
        id = id,
        productId = productId,
        quantity = quantity,
        unit = unit,
        locationId = locationId,
        expirationDate = expirationDate,
        purchaseDate = purchaseDate,
        storeName = storeName,
        price = price,
        notes = notes
    )
