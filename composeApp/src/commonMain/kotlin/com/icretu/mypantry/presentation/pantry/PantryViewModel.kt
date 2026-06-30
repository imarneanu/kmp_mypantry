package com.icretu.mypantry.presentation.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.usecase.AddPantryItemUseCase
import com.icretu.mypantry.domain.usecase.DeletePantryItemUseCase
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
) : ViewModel() {
    private val _state = MutableStateFlow(PantryState())
    val state: StateFlow<PantryState> = _state.asStateFlow()

    init {
        observeItems()
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
                        form = form.copy(location = intent.value),
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
                        form = form.copy(category = intent.value),
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
        updateState {
            copy(
                isFormVisible = true,
                form = PantryItemFormState(),
                errorMessage = null
            )
        }
    }

    private fun openEditForm(item: PantryItemUiModel) {
        updateState {
            copy(
                isFormVisible = true,
                form = PantryItemFormState(
                    id = item.id,
                    name = item.name,
                    quantity = item.quantity,
                    unit = item.unit,
                    location = item.location,
                    category = item.category,
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
        updateState {
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
            updateState { copy(errorMessage = "Name cannot be empty") }
            return
        }

        if (quantity == null || quantity <= 0) {
            updateState { copy(errorMessage = "Quantity must be greater than 0") }
            return
        }

        viewModelScope.launch {
            val item = PantryItem(
                id = form.id ?: 0,
                name = form.name.trim(),
                quantity = quantity,
                unit = form.unit.trim(),
                location = form.location,
                category = form.category,
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
        updateState {
            copy(form = form.reducer())
        }
    }

    private fun deleteItem(item: PantryItemUiModel) {
        viewModelScope.launch {
            deletePantryItemUseCase(item.id)
        }
    }

    private fun updateState(reducer: PantryState.() -> PantryState) {
        _state.value = _state.value.reducer()
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

}
