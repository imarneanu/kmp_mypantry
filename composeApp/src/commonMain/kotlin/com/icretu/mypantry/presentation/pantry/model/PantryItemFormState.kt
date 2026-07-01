package com.icretu.mypantry.presentation.pantry.model

import kotlinx.datetime.LocalDate

data class PantryItemFormState(
    val id: Long? = null,
    val name: String = "",
    val quantity: String = "",
    val unit: String = "pcs",
    val locationId: Long? = null,
    val categoryId: Long? = null,
    val expirationDate: LocalDate? = null,
    val storeName: String = "",
    val price: String = "",
    val notes: String = ""
)
