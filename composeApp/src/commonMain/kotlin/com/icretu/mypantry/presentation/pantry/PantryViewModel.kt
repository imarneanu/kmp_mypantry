package com.icretu.mypantry.presentation.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.usecase.AddPantryItemUseCase
import com.icretu.mypantry.domain.usecase.DeletePantryItemUseCase
import com.icretu.mypantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.domain.usecase.ObservePantryItemsUseCase
import com.icretu.mypantry.domain.usecase.UpdatePantryItemUseCase
import com.icretu.mypantry.presentation.pantry.model.PantryItemFormState
import com.icretu.mypantry.utils.updateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PantryViewModel(
    private val observePantryItemsUseCase: ObservePantryItemsUseCase,
    private val addPantryItemUseCase: AddPantryItemUseCase,
    private val updatePantryItemUseCase: UpdatePantryItemUseCase,
    private val deletePantryItemUseCase: DeletePantryItemUseCase,
    private val observeLocationsUseCase: ObserveLocationsUseCase,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PantryState())
    val state: StateFlow<PantryState> = _state.asStateFlow()

    init {
        observeItems()
        observeLocations()
        observeCategories()
    }

    fun onIntent(intent: PantryIntent) {
        when (intent) {
            PantryIntent.AddClicked -> openAddForm()

            is PantryIntent.ItemClicked -> openEditForm(intent.item)

            PantryIntent.FormDismissed -> closeForm()

            is PantryIntent.NameChanged ->
                updateForm { copy(name = intent.value) }

            is PantryIntent.QuantityChanged ->
                updateForm { copy(quantity = intent.value) }

            is PantryIntent.UnitChanged ->
                updateForm { copy(unit = intent.value) }

            is PantryIntent.StoreNameChanged ->
                updateForm { copy(storeName = intent.value) }

            is PantryIntent.PriceChanged ->
                updateForm { copy(price = intent.value) }

            is PantryIntent.NotesChanged ->
                updateForm { copy(notes = intent.value) }

            PantryIntent.ShowLocationDropdown ->
                _state.updateState { copy(isLocationDropdownExpanded = true) }

            PantryIntent.HideLocationDropdown ->
                _state.updateState { copy(isLocationDropdownExpanded = false) }

            is PantryIntent.LocationSelected ->
                _state.updateState {
                    copy(
                        form = form.copy(locationId = intent.id),
                        isLocationDropdownExpanded = false
                    )
                }

            PantryIntent.ShowCategoryDropdown ->
                _state.updateState { copy(isCategoryDropdownExpanded = true) }

            PantryIntent.HideCategoryDropdown ->
                _state.updateState { copy(isCategoryDropdownExpanded = false) }

            is PantryIntent.CategorySelected ->
                _state.updateState {
                    copy(
                        form = form.copy(categoryId = intent.id),
                        isCategoryDropdownExpanded = false
                    )
                }

            PantryIntent.ShowDatePicker ->
                _state.updateState { copy(isDatePickerVisible = true) }

            PantryIntent.HideDatePicker ->
                _state.updateState { copy(isDatePickerVisible = false) }

            is PantryIntent.ExpirationDateSelected ->
                _state.updateState {
                    copy(
                        form = form.copy(expirationDate = intent.date),
                        isDatePickerVisible = false
                    )
                }

            is PantryIntent.SearchChanged ->
                _state.updateState { copy(searchQuery = intent.value) }

            PantryIntent.SaveClicked -> saveForm()

            is PantryIntent.DeleteItem -> deleteItem(intent.item)
        }
    }

    private fun openAddForm() {
        _state.updateState {
            copy(
                isFormVisible = true,
                form = PantryItemFormState(),
                errorMessage = null
            )
        }
    }

    private fun openEditForm(item: PantryItemUiModel) {
        _state.updateState {
            copy(
                isFormVisible = true,
                form = PantryItemFormState(
                    id = item.id,
                    name = item.name,
                    quantity = item.quantity,
                    unit = item.unit,
                    locationId = item.locationId,
                    categoryId = item.categoryId,
                    expirationDate = item.expirationDate,
                    storeName = item.storeName.orEmpty(),
                    price = item.price.orEmpty(),
                    notes = item.notes.orEmpty()
                ),
                errorMessage = null
            )
        }
    }

    private fun closeForm() {
        _state. updateState {
            copy(
                isFormVisible = false,
                form = PantryItemFormState(),
                isDatePickerVisible = false,
                isLocationDropdownExpanded = false,
                isCategoryDropdownExpanded = false,
                errorMessage = null
            )
        }
    }

    private fun saveForm() {
        val form = _state.value.form
        val quantity = form.quantity.toIntOrNull()
        val price = form.price.toDoubleOrNull()

        if (form.name.isBlank()) {
            _state.updateState { copy(errorMessage = "Name cannot be empty") }
            return
        }

        if (quantity == null || quantity <= 0) {
            _state.updateState { copy(errorMessage = "Quantity must be greater than 0") }
            return
        }

        if (form.locationId == null) {
            _state.updateState { copy(errorMessage = "Select a location") }
            return
        }

        if (form.categoryId == null) {
            _state.updateState { copy(errorMessage = "Select a category") }
            return
        }

        val location = _state.value.locationOptions.first { it.id == form.locationId }
        val category = _state.value.categoryOptions.first { it.id == form.categoryId }

        viewModelScope.launch {
            val item = PantryItem(
                id = form.id ?: 0,
                name = form.name.trim(),
                quantity = quantity,
                unit = form.unit.trim(),
                locationId = location.id,
                locationName = location.name,
                categoryId = category.id,
                categoryName = category.name,
                expirationDate = form.expirationDate,
                storeName = form.storeName.takeIf { it.isNotBlank() },
                price = price,
                notes = form.notes.takeIf { it.isNotBlank() }
            )

            if (form.id == null) {
                addPantryItemUseCase(item)
            } else {
                updatePantryItemUseCase(item)
            }

            closeForm()
        }
    }

    private fun updateForm(
        reducer: PantryItemFormState.() -> PantryItemFormState
    ) {
        _state.updateState {
            copy(form = form.reducer())
        }
    }

    private fun deleteItem(item: PantryItemUiModel) {
        viewModelScope.launch {
            deletePantryItemUseCase(item.id)
        }
    }

    private fun observeItems() {
        viewModelScope.launch {
            observePantryItemsUseCase()
                .collect { items ->
                    _state.updateState {
                        copy(
                            items = items.map { it.toUiModel() },
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun observeLocations() {
        viewModelScope.launch {
            observeLocationsUseCase().collect { locations ->
                _state.updateState { copy(locationOptions = locations) }
            }
        }
    }

    private fun observeCategories() {
        viewModelScope.launch {
            observeCategoriesUseCase().collect { categories ->
                _state.updateState { copy(categoryOptions = categories) }
            }
        }
    }

}
