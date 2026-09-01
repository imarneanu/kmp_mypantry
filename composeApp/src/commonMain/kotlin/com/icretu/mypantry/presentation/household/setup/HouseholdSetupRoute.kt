package com.icretu.mypantry.presentation.household.setup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HouseholdSetupRoute() {
    val viewModel: HouseholdSetupViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    HouseholdSetupScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}
