package com.icretu.mypantry.presentation.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.usecase.AddPantryItemUseCase
import com.icretu.mypantry.domain.usecase.DeletePantryItemUseCase
import com.icretu.mypantry.domain.usecase.ObservePantryItemsUseCase
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
            PantryIntent.AddSampleItem -> addSampleItem()
            is PantryIntent.DeleteItem -> deleteItem(intent.item)
        }
    }

    private fun observeItems() {
        viewModelScope.launch {
            observePantryItemsUseCase()
                .collect { items ->
                    _state.value = PantryState(
                        items = items,
                        isLoading = false
                    )
                }
        }
    }

    private fun addSampleItem() {
        viewModelScope.launch {
            addPantryItemUseCase(
                PantryItem(
                    name = "Flour",
                    quantity = 2,
                    unit = "kg",
                    location = "Pantry",
                    category = "Essentials"
                )
            )
        }
    }

    private fun deleteItem(item: PantryItem) {
        viewModelScope.launch {
            deletePantryItemUseCase(item)
        }
    }
}
