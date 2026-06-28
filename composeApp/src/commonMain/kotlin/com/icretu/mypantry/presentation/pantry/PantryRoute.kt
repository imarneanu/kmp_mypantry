package com.icretu.mypantry.presentation.pantry

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PantryRoute() {
    val viewModel: PantryViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    PantryScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}
