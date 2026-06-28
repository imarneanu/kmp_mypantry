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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.presentation.pantry.PantryIntent
import com.icretu.mypantry.presentation.pantry.PantryState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPantryItemSheet(
    state: PantryState,
    onIntent: (PantryIntent) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onIntent(PantryIntent.HideAddSheet)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Add pantry item",
                style = MaterialTheme.typography.titleLarge
            )

            OutlinedTextField(
                value = state.nameInput,
                onValueChange = { onIntent(PantryIntent.NameChanged(it)) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.quantityInput,
                onValueChange = { onIntent(PantryIntent.QuantityChanged(it)) },
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.unitInput,
                onValueChange = { onIntent(PantryIntent.UnitChanged(it)) },
                label = { Text("Unit") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.locationInput,
                onValueChange = { onIntent(PantryIntent.LocationChanged(it)) },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.categoryInput,
                onValueChange = { onIntent(PantryIntent.CategoryChanged(it)) },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth()
            )

            state.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            Button(
                onClick = { onIntent(PantryIntent.SaveItem) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save item")
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
