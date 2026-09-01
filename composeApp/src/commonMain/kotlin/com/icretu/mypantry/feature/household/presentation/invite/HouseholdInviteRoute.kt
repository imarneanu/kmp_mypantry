package com.icretu.mypantry.feature.household.presentation.invite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HouseholdInviteRoute(
    onBack: () -> Unit
) {
    val viewModel: HouseholdInviteViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    HouseholdInviteScreen(
        state = state,
        onCreateInvite = viewModel::createInvite,
        onBack = onBack
    )
}
