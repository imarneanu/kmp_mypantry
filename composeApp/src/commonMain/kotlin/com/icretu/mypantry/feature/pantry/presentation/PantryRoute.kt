package com.icretu.mypantry.feature.pantry.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun PantryRoute(
    viewModel: PantryViewModel,
    onNavigateToForm: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                PantryEffect.NavigateToForm -> onNavigateToForm()
                PantryEffect.NavigateBack -> Unit
            }
        }
    }

    PantryScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}
