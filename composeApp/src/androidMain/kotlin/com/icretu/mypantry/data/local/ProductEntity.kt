package com.icretu.mypantry.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.icretu.mypantry.domain.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val brand: String? = null,
    val categoryId: Long,
    val defaultUnit: String = "pcs",
    val barcode: String? = null,
    val notes: String? = null
)

fun ProductEntity.toDomain(categoryName: String = "") =
    Product(
        id = id,
        name = name,
        brand = brand,
        categoryId = categoryId,
        categoryName = categoryName,
        defaultUnit = defaultUnit,
        barcode = barcode,
        notes = notes
    )


fun Product.toEntity() =
    ProductEntity(
        id = id,
        name = name,
        brand = brand,
        categoryId = categoryId,
        defaultUnit = defaultUnit,
        barcode = barcode,
        notes = notes
    )
