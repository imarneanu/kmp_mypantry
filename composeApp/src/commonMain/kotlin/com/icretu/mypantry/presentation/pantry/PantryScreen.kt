package com.icretu.mypantry.presentation.pantry

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.presentation.pantry.components.PantryItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryScreen(
    state: PantryState,
    onIntent: (PantryIntent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Pantry") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onIntent(PantryIntent.AddSampleItem) }
            ) {
                Text("+")
            }
        }
    ) { padding ->
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    CircularProgressIndicator()
                }
            }

            state.items.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Text("No pantry items yet. Tap + to add one.")
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.items) { item ->
                        PantryItemCard(
                            item = item,
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
