package com.icretu.mypantry.presentation.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.presentation.pantry.components.ExpirationDatePickerDialog
import com.icretu.mypantry.presentation.pantry.components.PantryDropdownField
import com.icretu.mypantry.presentation.pantry.components.ProductSelectorField
import com.icretu.mypantry.utils.DateUtils.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockEntryFormScreen(
    state: PantryState,
    onIntent: (PantryIntent) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val form = state.form
    val isEditing = form.stockEntryId != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isEditing) {
                            "Edit stock entry"
                        } else {
                            "Add stock entry"
                        }
                    )
                },
                navigationIcon = {
                    TextButton(onClick = onBackClick) {
                        Text("Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(
                start = 24.dp,
                end = 24.dp,
                top = 16.dp,
                bottom = 32.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ProductSelectorField(
                    value = form.productName,
                    products = state.products,
                    expanded = state.isProductDropdownExpanded,
                    onValueChange = {
                        onIntent(PantryIntent.ProductNameChanged(it))
                    },
                    onExpandedChange = { expanded ->
                        onIntent(
                            if (expanded) {
                                PantryIntent.ShowProductDropdown
                            } else {
                                PantryIntent.HideProductDropdown
                            }
                        )
                    },
                    onProductSelected = { productId ->
                        onIntent(PantryIntent.ProductSelected(productId))
                    }
                )
            }

            item {
                OutlinedTextField(
                    value = form.productBrand,
                    onValueChange = {
                        onIntent(PantryIntent.ProductBrandChanged(it))
                    },
                    label = { Text("Brand") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = form.quantity,
                    onValueChange = {
                        onIntent(PantryIntent.QuantityChanged(it))
                    },
                    label = { Text("Quantity") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = form.unit,
                    onValueChange = {
                        onIntent(PantryIntent.UnitChanged(it))
                    },
                    label = { Text("Unit") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                PantryDropdownField(
                    label = "Location",
                    value = state.locationOptions
                        .firstOrNull { it.id == form.locationId }
                        ?.name
                        .orEmpty(),
                    options = state.locationOptions.map { it.id to it.name },
                    expanded = state.isLocationDropdownExpanded,
                    onExpandedChange = { expanded ->
                        onIntent(
                            if (expanded) {
                                PantryIntent.ShowLocationDropdown
                            } else {
                                PantryIntent.HideLocationDropdown
                            }
                        )
                    },
                    onOptionSelected = {
                        onIntent(PantryIntent.LocationSelected(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                PantryDropdownField(
                    label = "Category",
                    value = state.categoryOptions
                        .firstOrNull { it.id == form.categoryId }
                        ?.name
                        .orEmpty(),
                    options = state.categoryOptions.map { it.id to it.name },
                    expanded = state.isCategoryDropdownExpanded,
                    onExpandedChange = { expanded ->
                        onIntent(
                            if (expanded) {
                                PantryIntent.ShowCategoryDropdown
                            } else {
                                PantryIntent.HideCategoryDropdown
                            }
                        )
                    },
                    onOptionSelected = {
                        onIntent(PantryIntent.CategorySelected(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedButton(
                    onClick = {
                        onIntent(PantryIntent.ShowDatePicker)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Expiration: ${formatDate(form.expirationDate)}"
                    )
                }
            }

            item {
                OutlinedTextField(
                    value = form.storeName,
                    onValueChange = {
                        onIntent(PantryIntent.StoreNameChanged(it))
                    },
                    label = { Text("Store") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = form.price,
                    onValueChange = {
                        onIntent(PantryIntent.PriceChanged(it))
                    },
                    label = { Text("Price") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = form.notes,
                    onValueChange = {
                        onIntent(PantryIntent.NotesChanged(it))
                    },
                    label = { Text("Notes") },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            state.errorMessage?.let { message ->
                item {
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            item {
                Button(
                    onClick = {
                        onIntent(PantryIntent.SaveClicked)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (isEditing) {
                            "Update stock entry"
                        } else {
                            "Save stock entry"
                        }
                    )
                }
            }
        }
    }

    if (state.isDatePickerVisible) {
        ExpirationDatePickerDialog(
            selectedDate = state.form.expirationDate,
            onDateSelected = {
                onIntent(PantryIntent.ExpirationDateSelected(it))
            },
            onDismiss = {
                onIntent(PantryIntent.HideDatePicker)
            }
        )
    }
}
