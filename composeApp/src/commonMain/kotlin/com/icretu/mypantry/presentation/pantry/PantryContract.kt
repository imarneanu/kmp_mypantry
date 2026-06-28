package com.icretu.mypantry.presentation.pantry

import kotlinx.datetime.LocalDate

data class PantryState(
    val items: List<PantryItemUiModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val isAddSheetVisible: Boolean = false,

    val nameInput: String = "",
    val quantityInput: String = "",
    val unitInput: String = "pcs",

    val locationInput: String = "Pantry",
    val locationOptions: List<String> = listOf(
        "Pantry",
        "Freezer",
        "Cupboard",
        "Bathroom"
    ),
    val isLocationDropdownExpanded: Boolean = false,

    val categoryInput: String = "Essentials",
    val categoryOptions: List<String> = listOf(
        "Essentials",
        "Frozen food",
        "Tea & coffee",
        "Toiletries",
        "Cleaning products"
    ),
    val isCategoryDropdownExpanded: Boolean = false,

    val isDatePickerVisible: Boolean = false,
    val expirationDate: LocalDate? = null,

    val searchQuery: String = "",
)

sealed interface PantryIntent {
    data object ShowAddSheet : PantryIntent
    data object HideAddSheet : PantryIntent

    data object ShowLocationDropdown : PantryIntent
    data object HideLocationDropdown : PantryIntent
    data class LocationSelected(val value: String) : PantryIntent

    data object ShowCategoryDropdown : PantryIntent
    data object HideCategoryDropdown : PantryIntent
    data class CategorySelected(val value: String) : PantryIntent

    data object ShowDatePicker : PantryIntent
    data object HideDatePicker : PantryIntent
    data class ExpirationDateSelected(val date: LocalDate?) : PantryIntent

    data class NameChanged(val value: String) : PantryIntent
    data class QuantityChanged(val value: String) : PantryIntent
    data class UnitChanged(val value: String) : PantryIntent

    data class SearchChanged(val value: String) : PantryIntent

    data object SaveItem : PantryIntent
    data class DeleteItem(val item: PantryItemUiModel) : PantryIntent
}
