package com.icretu.mypantry.domain.model

data class Product(
    val id: Long = 0,
    val name: String,
    val brand: String? = null,
    val categoryId: Long,
    val categoryName: String,
    val defaultUnit: String = "pcs",
    val barcode: String? = null,
    val notes: String? = null
)
