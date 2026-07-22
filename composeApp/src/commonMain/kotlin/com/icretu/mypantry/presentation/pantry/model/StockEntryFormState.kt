package com.icretu.mypantry.presentation.pantry.model

import kotlinx.datetime.LocalDate

data class StockEntryFormState(
    val stockEntryId: Long? = null,

    val productId: Long? = null,
    val productName: String = "",
    val productBrand: String = "",
    val quantity: String = "",
    val unit: String = "pcs",

    val locationId: Long? = null,
    val categoryId: Long? = null,

    val expirationDate: LocalDate? = null,
    val storeName: String = "",
    val price: String = "",
    val notes: String = ""
)
