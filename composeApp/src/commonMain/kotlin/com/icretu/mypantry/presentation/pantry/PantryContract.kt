package com.icretu.mypantry.presentation.pantry

import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.StorageLocation
import com.icretu.mypantry.presentation.pantry.model.PantryItemFormState
import kotlinx.datetime.LocalDate

data class PantryState(
    val items: List<PantryItemUiModel> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null,

    val searchQuery: String = "",

    val isFormVisible: Boolean = false,
    val form: PantryItemFormState = PantryItemFormState(),

    val locationOptions: List<StorageLocation> = emptyList(),
    val isLocationDropdownExpanded: Boolean = false,

    val categoryOptions: List<Category> = emptyList(),
    val isCategoryDropdownExpanded: Boolean = false,

    val isDatePickerVisible: Boolean = false,

    )

sealed interface PantryIntent {
    data object AddClicked : PantryIntent
    data class ItemClicked(val item: PantryItemUiModel) : PantryIntent
    data object FormDismissed : PantryIntent

    data class NameChanged(val value: String) : PantryIntent
    data class QuantityChanged(val value: String) : PantryIntent
    data class UnitChanged(val value: String) : PantryIntent
    data class StoreNameChanged(val value: String) : PantryIntent
    data class PriceChanged(val value: String) : PantryIntent
    data class NotesChanged(val value: String) : PantryIntent

    data object ShowLocationDropdown : PantryIntent
    data object HideLocationDropdown : PantryIntent
    data class LocationSelected(val id: Long) : PantryIntent

    data object ShowCategoryDropdown : PantryIntent
    data object HideCategoryDropdown : PantryIntent
    data class CategorySelected(val id: Long) : PantryIntent

    data object ShowDatePicker : PantryIntent
    data object HideDatePicker : PantryIntent
    data class ExpirationDateSelected(val date: LocalDate?) : PantryIntent

    data class SearchChanged(val value: String) : PantryIntent

    data object SaveClicked : PantryIntent
    data class DeleteItem(val item: PantryItemUiModel) : PantryIntent
}
