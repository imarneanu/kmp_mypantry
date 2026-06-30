package com.icretu.mypantry.presentation.pantry.model

import kotlinx.datetime.LocalDate

data class PantryItemFormState(
    val id: Long? = null,
    val name: String = "",
    val quantity: String = "",
    val unit: String = "pcs",
    val location: String = "Pantry",
    val category: String = "Essentials",
    val expirationDate: LocalDate? = null,
    val storeName: String = "",
    val price: String = "",
    val notes: String = ""
)
