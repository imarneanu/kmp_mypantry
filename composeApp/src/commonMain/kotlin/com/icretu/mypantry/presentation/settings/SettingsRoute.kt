package com.icretu.mypantry.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsRoute(
    onInviteFamilyMember: () -> Unit
) {
    val viewModel: SettingsViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.effects.collect { effect ->
            when (effect) {
                SettingsEffect.OpenHouseholdInvite ->
                    onInviteFamilyMember()
            }
        }
    }

    SettingsScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}
