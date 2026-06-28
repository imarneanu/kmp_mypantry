package com.icretu.mypantry.presentation.pantry

import com.icretu.mypantry.domain.model.PantryItem

data class PantryState(
    val items: List<PantryItem> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val isAddSheetVisible: Boolean = false,

    val nameInput: String = "",
    val quantityInput: String = "",
    val unitInput: String = "pcs",
    val locationInput: String = "Pantry",
    val categoryInput: String = "Essentials"
)

sealed interface PantryIntent {
    data object ShowAddSheet : PantryIntent
    data object HideAddSheet : PantryIntent

    data class NameChanged(val value: String) : PantryIntent
    data class QuantityChanged(val value: String) : PantryIntent
    data class UnitChanged(val value: String) : PantryIntent
    data class LocationChanged(val value: String) : PantryIntent
    data class CategoryChanged(val value: String) : PantryIntent

    data object SaveItem : PantryIntent
    data class DeleteItem(val item: PantryItem) : PantryIntent
}
