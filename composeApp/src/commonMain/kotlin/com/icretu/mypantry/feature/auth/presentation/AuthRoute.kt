package com.icretu.mypantry.feature.auth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthRoute() {
    val viewModel: AuthViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    AuthScreen(
        state = state,
        onIntent = viewModel::onIntent
    )
}
