package com.icretu.mypantry.presentation.pantry.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.presentation.pantry.PantryIntent
import com.icretu.mypantry.presentation.pantry.PantryState
import com.icretu.mypantry.utils.DateUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockEntryFormSheet(
    state: PantryState,
    onIntent: (PantryIntent) -> Unit
) {
    val form = state.form
    val isEditing = form.stockEntryId != null

    ModalBottomSheet(
        onDismissRequest = {
            onIntent(PantryIntent.FormDismissed)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = if (isEditing) "Edit stock entry" else "Add stock entry",
                style = MaterialTheme.typography.titleLarge
            )

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

            OutlinedTextField(
                value = form.productBrand,
                onValueChange = {
                    onIntent(PantryIntent.ProductBrandChanged(it))
                },
                label = { Text("Brand") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = form.quantity,
                onValueChange = { onIntent(PantryIntent.QuantityChanged(it)) },
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = form.unit,
                onValueChange = { onIntent(PantryIntent.UnitChanged(it)) },
                label = { Text("Unit") },
                modifier = Modifier.fillMaxWidth()
            )

            PantryDropdownField(
                label = "Location",
                value = state.locationOptions
                    .firstOrNull { it.id == state.form.locationId }
                    ?.name
                    .orEmpty(),
                options = state.locationOptions.map { it.id to it.name },
                expanded = state.isLocationDropdownExpanded,
                onExpandedChange = { expanded ->
                    onIntent(
                        if (expanded) PantryIntent.ShowLocationDropdown
                        else PantryIntent.HideLocationDropdown
                    )
                },
                onOptionSelected = { id ->
                    onIntent(PantryIntent.LocationSelected(id))
                },
                modifier = Modifier.fillMaxWidth()
            )

            PantryDropdownField(
                label = "Category",
                value = state.categoryOptions
                    .firstOrNull { it.id == state.form.categoryId }
                    ?.name
                    .orEmpty(),
                options = state.categoryOptions.map { it.id to it.name },
                expanded = state.isCategoryDropdownExpanded,
                onExpandedChange = { expanded ->
                    onIntent(
                        if (expanded) PantryIntent.ShowCategoryDropdown
                        else PantryIntent.HideCategoryDropdown
                    )
                },
                onOptionSelected = { id ->
                    onIntent(PantryIntent.CategorySelected(id))
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(
                onClick = { onIntent(PantryIntent.ShowDatePicker) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Expiration: ${DateUtils.formatDate(form.expirationDate)}")
            }

            OutlinedTextField(
                value = form.storeName,
                onValueChange = {
                    onIntent(PantryIntent.StoreNameChanged(it))
                },
                label = { Text("Store") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = form.price,
                onValueChange = {
                    onIntent(PantryIntent.PriceChanged(it))
                },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = form.notes,
                onValueChange = {
                    onIntent(PantryIntent.NotesChanged(it))
                },
                label = { Text("Notes") },
                modifier = Modifier.fillMaxWidth()
            )

            state.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = { onIntent(PantryIntent.SaveClicked) },
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

            Spacer(Modifier.height(16.dp))
        }
    }
}
