package com.icretu.mypantry.presentation.pantry

import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.Product
import com.icretu.mypantry.domain.model.StorageLocation
import com.icretu.mypantry.presentation.pantry.model.StockEntryFormState
import kotlinx.datetime.LocalDate

data class PantryState(

    val items: List<StockEntryUiModel> = emptyList(),
    val products: List<Product> = emptyList(),

    val isLoading: Boolean = true,
    val errorMessage: String? = null,

    val searchQuery: String = "",

    val isFormVisible: Boolean = false,
    val form: StockEntryFormState = StockEntryFormState(),

    val locationOptions: List<StorageLocation> = emptyList(),
    val isLocationDropdownExpanded: Boolean = false,

    val categoryOptions: List<Category> = emptyList(),
    val isCategoryDropdownExpanded: Boolean = false,

    val isProductDropdownExpanded: Boolean = false,

    val isDatePickerVisible: Boolean = false,
)

sealed interface PantryIntent {
    data object AddClicked : PantryIntent
    data class ItemClicked(val item: StockEntryUiModel) : PantryIntent
    data object FormDismissed : PantryIntent

    data class ProductNameChanged(val value: String) : PantryIntent
    data class ProductBrandChanged(val value: String) : PantryIntent
    data class ProductSelected(val productId: Long) : PantryIntent

    data class QuantityChanged(val value: String) : PantryIntent
    data class UnitChanged(val value: String) : PantryIntent
    data class StoreNameChanged(val value: String) : PantryIntent
    data class PriceChanged(val value: String) : PantryIntent
    data class NotesChanged(val value: String) : PantryIntent

    data object ShowProductDropdown : PantryIntent
    data object HideProductDropdown : PantryIntent

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
    data class DeleteItem(val item: StockEntryUiModel) : PantryIntent
}
