package com.icretu.mypantry.feature.locations.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LocationsRoute() {
    val viewModel: LocationsViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LocationsScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}
