package com.icretu.mypantry.presentation.pantry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.presentation.pantry.components.ExpirationDatePickerDialog
import com.icretu.mypantry.presentation.pantry.components.StockEntryCard
import com.icretu.mypantry.presentation.pantry.components.StockEntryFormSheet
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryScreen(
    state: PantryState,
    onIntent: (PantryIntent) -> Unit
) {
    val visibleItems = state.items
        .filter { item ->
            state.searchQuery.isBlank() ||
                    item.productName.contains(state.searchQuery, ignoreCase = true) ||
                    item.categoryName.contains(state.searchQuery, ignoreCase = true) ||
                    item.locationName.contains(state.searchQuery, ignoreCase = true)
        }
        .sortedWith(
            compareBy<StockEntryUiModel> {
                it.expirationDate
                    ?: LocalDate(9999, 12, 31)
            }.thenBy { it.productName }
        )

    val groupedItems = visibleItems.groupBy { it.locationName }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.items.isEmpty() -> {
                Text(
                    text = "No pantry items yet. Tap + to add one.",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                )
            }

            else -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    OutlinedTextField(
                        value = state.searchQuery,
                        onValueChange = { onIntent(PantryIntent.SearchChanged(it)) },
                        label = { Text("Search pantry") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        groupedItems.forEach { (location, items) ->
                            item {
                                Text(
                                    text = location,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            items(items) { item ->
                                StockEntryCard(
                                    item = item,
                                    onClick = {
                                        onIntent(PantryIntent.ItemClicked(item))
                                    },
                                    onDelete = {
                                        onIntent(PantryIntent.DeleteItem(item))
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { onIntent(PantryIntent.AddClicked) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+")
        }
    }

    if (state.isFormVisible) {
        StockEntryFormSheet(
            state = state,
            onIntent = onIntent
        )
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
