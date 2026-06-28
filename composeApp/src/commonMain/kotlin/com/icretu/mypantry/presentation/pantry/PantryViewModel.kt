package com.icretu.mypantry.presentation.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.usecase.AddPantryItemUseCase
import com.icretu.mypantry.domain.usecase.DeletePantryItemUseCase
import com.icretu.mypantry.domain.usecase.ObservePantryItemsUseCase
import com.icretu.mypantry.utils.updateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PantryViewModel(
    private val observePantryItemsUseCase: ObservePantryItemsUseCase,
    private val addPantryItemUseCase: AddPantryItemUseCase,
    private val deletePantryItemUseCase: DeletePantryItemUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PantryState())
    val state: StateFlow<PantryState> = _state.asStateFlow()

    init {
        observeItems()
    }

    fun onIntent(intent: PantryIntent) {
        when (intent) {
            PantryIntent.ShowAddSheet -> showAddSheet()

            PantryIntent.HideAddSheet -> hideAddSheet()

            is PantryIntent.NameChanged -> _state.updateState { copy(nameInput = intent.value) }

            is PantryIntent.QuantityChanged -> _state.updateState { copy(quantityInput = intent.value) }

            is PantryIntent.UnitChanged -> _state.updateState { copy(unitInput = intent.value) }

            is PantryIntent.LocationChanged -> _state.updateState { copy(locationInput = intent.value) }

            is PantryIntent.CategoryChanged -> _state.updateState { copy(categoryInput = intent.value) }

            PantryIntent.SaveItem -> saveItem()

            is PantryIntent.DeleteItem -> deleteItem(intent.item)

            PantryIntent.ShowDatePicker ->
                _state.updateState { copy(isDatePickerVisible = true) }

            PantryIntent.HideDatePicker ->
                _state.updateState { copy(isDatePickerVisible = false) }

            is PantryIntent.ExpirationDateSelected ->
                _state.updateState {
                    copy(
                        expirationDate = intent.date,
                        isDatePickerVisible = false
                    )
                }
        }
    }

    private fun showAddSheet() {
        _state.updateState {
            copy(
                isAddSheetVisible = true,
                errorMessage = null
            )
        }
    }

    private fun hideAddSheet() {
        _state.updateState {
            copy(
                isAddSheetVisible = false,
                errorMessage = null
            )
        }

    }

    private fun saveItem() {
        val currentState = _state.value
        val quantity = currentState.quantityInput.toIntOrNull()

        if (currentState.nameInput.isBlank()) {
            _state.updateState { copy(errorMessage = "Name cannot be empty") }
            return
        }

        if (quantity == null || quantity <= 0) {
            _state.updateState { copy(errorMessage = "Quantity must be greater than 0") }
            return
        }

        viewModelScope.launch {
            addPantryItemUseCase(
                PantryItem(
                    name = currentState.nameInput.trim(),
                    quantity = quantity,
                    unit = currentState.unitInput.trim(),
                    location = currentState.locationInput.trim(),
                    category = currentState.categoryInput.trim(),
                    expirationDate = currentState.expirationDate,
                )
            )

            _state.updateState {
                copy(
                    isAddSheetVisible = false,
                    nameInput = "",
                    quantityInput = "",
                    unitInput = "pcs",
                    locationInput = "Pantry",
                    categoryInput = "Essentials",
                    expirationDate = null,
                    isDatePickerVisible = false,
                    errorMessage = null,
                )
            }
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

    private fun deleteItem(item: PantryItemUiModel) {
        viewModelScope.launch {
            deletePantryItemUseCase(item.id)
        }
    }
}
