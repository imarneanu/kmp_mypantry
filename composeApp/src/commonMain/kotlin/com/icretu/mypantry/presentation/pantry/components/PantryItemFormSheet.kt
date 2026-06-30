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
fun PantryItemFormSheet(
    state: PantryState,
    onIntent: (PantryIntent) -> Unit
) {
    val form = state.form
    val title = if (form.id == null) "Add item" else "Edit item"

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
                text = title,
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = form.name,
                onValueChange = { onIntent(PantryIntent.NameChanged(it)) },
                label = { Text("Name") },
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
                value = form.location,
                options = state.locationOptions,
                expanded = state.isLocationDropdownExpanded,
                onExpandedChange = { expanded ->
                    onIntent(
                        if (expanded) PantryIntent.ShowLocationDropdown
                        else PantryIntent.HideLocationDropdown
                    )
                },
                onOptionSelected = {
                    onIntent(PantryIntent.LocationSelected(it))
                },
                modifier = Modifier.fillMaxWidth()
            )

            PantryDropdownField(
                label = "Category",
                value = form.category,
                options = state.categoryOptions,
                expanded = state.isCategoryDropdownExpanded,
                onExpandedChange = { expanded ->
                    onIntent(
                        if (expanded) PantryIntent.ShowCategoryDropdown
                        else PantryIntent.HideCategoryDropdown
                    )
                },
                onOptionSelected = {
                    onIntent(PantryIntent.CategorySelected(it))
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(
                onClick = { onIntent(PantryIntent.ShowDatePicker) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Expiration: ${DateUtils.formatDate(form.expirationDate)}")
            }

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
                Text(if (form.id == null) "Save item" else "Update item")
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
