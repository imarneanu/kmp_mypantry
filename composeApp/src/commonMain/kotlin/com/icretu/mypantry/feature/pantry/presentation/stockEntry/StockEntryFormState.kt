package com.icretu.mypantry.feature.pantry.presentation.stockEntry

import kotlinx.datetime.LocalDate

data class StockEntryFormState(
    val stockEntryId: String? = null,

    val productId: String? = null,
    val productName: String = "",
    val productBrand: String = "",

    val quantity: String = "",
    val unit: String = "pcs",

    val locationId: String? = null,
    val categoryId: String? = null,

    val expirationDate: LocalDate? = null,
    val storeName: String = "",
    val price: String = "",
    val notes: String = "",
)
