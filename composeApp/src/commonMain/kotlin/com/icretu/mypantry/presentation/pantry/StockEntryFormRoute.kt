package com.icretu.mypantry.presentation.pantry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun StockEntryFormRoute(
    viewModel: PantryViewModel,
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                PantryEffect.NavigateBack -> onNavigateBack()
                PantryEffect.NavigateToForm -> Unit
            }
        }
    }

    StockEntryFormScreen(
        state = state,
        onIntent = viewModel::onIntent,
        onBackClick = {
            viewModel.onIntent(PantryIntent.FormDismissed)
        }
    )
}
