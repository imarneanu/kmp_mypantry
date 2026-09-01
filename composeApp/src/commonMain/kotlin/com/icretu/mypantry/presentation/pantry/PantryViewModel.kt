package com.icretu.mypantry.presentation.pantry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.model.Product
import com.icretu.mypantry.domain.model.StockEntry
import com.icretu.mypantry.domain.model.UserSession
import com.icretu.mypantry.domain.model.toUiModel
import com.icretu.mypantry.domain.repository.SessionRepository
import com.icretu.mypantry.domain.usecase.DeleteStockEntryUseCase
import com.icretu.mypantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.domain.usecase.ObserveProductsUseCase
import com.icretu.mypantry.domain.usecase.ObserveStockEntriesUseCase
import com.icretu.mypantry.domain.usecase.UpsertProductUseCase
import com.icretu.mypantry.domain.usecase.UpsertStockEntryUseCase
import com.icretu.mypantry.domain.util.IdGenerator
import com.icretu.mypantry.domain.util.TimestampProvider
import com.icretu.mypantry.presentation.pantry.model.StockEntryFormState
import com.icretu.mypantry.utils.updateState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class PantryViewModel(
    private val observeStockEntriesUseCase: ObserveStockEntriesUseCase,
    private val observeProductsUseCase: ObserveProductsUseCase,
    private val observeLocationsUseCase: ObserveLocationsUseCase,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val upsertProductUseCase: UpsertProductUseCase,
    private val upsertStockEntryUseCase: UpsertStockEntryUseCase,
    private val deleteStockEntryUseCase: DeleteStockEntryUseCase,
    private val idGenerator: IdGenerator,
    private val timestampProvider: TimestampProvider,
    private val sessionRepository: SessionRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(PantryState())
    val state: StateFlow<PantryState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<PantryEffect>()
    val effects = _effects.asSharedFlow()

    init {
        observeStockEntries()
        observeProducts()
        observeLocations()
        observeCategories()
    }

    private fun observeStockEntries() {
        viewModelScope.launch {
            observeStockEntriesUseCase()
                .collect { entries ->
                    _state.updateState {
                        copy(
                            items = entries.map { it.toUiModel() },
                            isLoading = false,
                        )
                    }
                }
        }
    }

    private fun observeProducts() {
        viewModelScope.launch {
            observeProductsUseCase()
                .collect { products ->
                    _state.updateState {
                        copy(products = products)
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

    fun onIntent(intent: PantryIntent) {
        when (intent) {
            PantryIntent.AddClicked -> openAddForm()
            is PantryIntent.ItemClicked -> openEditForm(intent.item)
            PantryIntent.FormDismissed -> {
                closeForm()
                viewModelScope.launch {
                    _effects.emit(PantryEffect.NavigateBack)
                }
            }

            is PantryIntent.ProductNameChanged ->
                updateForm { copy(productName = intent.value, productId = null) }

            is PantryIntent.ProductBrandChanged ->
                updateForm { copy(productBrand = intent.value) }

            is PantryIntent.ProductSelected ->
                selectProduct(intent.productId)

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

            PantryIntent.ShowProductDropdown ->
                _state.updateState { copy(isProductDropdownExpanded = true) }

            PantryIntent.HideProductDropdown ->
                _state.updateState { copy(isProductDropdownExpanded = false) }

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
        val defaultLocationId = _state.value.locationOptions.firstOrNull()?.id
        val defaultCategoryId = _state.value.categoryOptions.firstOrNull()?.id

        _state.updateState {
            copy(
                isFormVisible = true,
                form = StockEntryFormState(
                    locationId = defaultLocationId,
                    categoryId = defaultCategoryId,
                ),
                errorMessage = null
            )
        }

        viewModelScope.launch {
            _effects.emit(PantryEffect.NavigateToForm)
        }
    }

    private fun openEditForm(item: StockEntryUiModel) {
        _state.updateState {
            copy(
                isFormVisible = true,
                form = StockEntryFormState(
                    stockEntryId = item.id,
                    productId = item.productId,
                    productName = item.productName,
                    productBrand = item.productBrand.orEmpty(),
                    quantity = item.quantity.toString(),
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

        viewModelScope.launch {
            _effects.emit(PantryEffect.NavigateToForm)
        }
    }

    private fun closeForm() {
        _state.updateState {
            copy(
                isFormVisible = false,
                form = StockEntryFormState(),
                isDatePickerVisible = false,
                isLocationDropdownExpanded = false,
                isCategoryDropdownExpanded = false,
                errorMessage = null
            )
        }
    }

    private fun selectProduct(productId: String) {
        val product = _state.value.products.firstOrNull { it.id == productId } ?: return

        _state.updateState {
            copy(
                form = form.copy(
                    productId = product.id,
                    productName = product.name,
                    productBrand = product.brand.orEmpty(),
                    categoryId = product.categoryId,
                    unit = product.defaultUnit
                ),
                isProductDropdownExpanded = false
            )
        }
    }

    private fun saveForm() {
        val form = _state.value.form
        val quantity = form.quantity.toIntOrNull()
        val price = form.price.toDoubleOrNull()

        if (form.productName.isBlank()) {
            _state.updateState { copy(errorMessage = "Product name cannot be empty") }
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

        viewModelScope.launch {
            val productId = form.productId ?: upsertProductUseCase(
                Product(
                    id = idGenerator.generate(),
                    name = form.productName.trim(),
                    brand = form.productBrand.takeIf { it.isNotBlank() },
                    categoryId = form.categoryId,
                    defaultUnit = form.unit.trim()
                )
            )

            val session = sessionRepository.session.firstOrNull()
                ?: error("No authenticated user")

            val householdId = session.householdId
                ?: error("No active household")

            upsertStockEntryUseCase(
                StockEntry(
                    id = form.stockEntryId ?: idGenerator.generate(),
                    householdId = householdId,
                    productId = productId,
                    quantity = quantity,
                    unit = form.unit.trim(),
                    locationId = form.locationId,
                    expirationDate = form.expirationDate,
                    storeName = form.storeName.takeIf { it.isNotBlank() },
                    price = price,
                    notes = form.notes.takeIf { it.isNotBlank() },
                    updatedAtEpochMillis = timestampProvider.nowEpochMillis(),
                    updatedBy = session.userId,
                )
            )

            closeForm()
            _effects.emit(PantryEffect.NavigateBack)
        }
    }

    private fun deleteItem(item: StockEntryUiModel) {
        viewModelScope.launch {
            deleteStockEntryUseCase(item.id)
        }
    }

    private fun updateForm(
        reducer: StockEntryFormState.() -> StockEntryFormState
    ) {
        _state.updateState {
            copy(form = form.reducer())
        }
    }

}
