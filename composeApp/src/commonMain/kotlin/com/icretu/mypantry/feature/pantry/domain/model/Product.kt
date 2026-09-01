package com.icretu.mypantry.feature.pantry.domain.model

data class Product(
    val id: String,
    val name: String,
    val brand: String? = null,
    val categoryId: String,
    val defaultUnit: String = "pcs",
    val barcode: String? = null,
    val notes: String? = null,
)
