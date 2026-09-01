package com.icretu.mypantry.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.icretu.mypantry.domain.repository.SessionRepository
import com.icretu.mypantry.navigation.AppNavigation
import com.icretu.mypantry.presentation.auth.AuthRoute
import org.koin.compose.koinInject

@Composable
fun RootScreen(
    sessionRepository: SessionRepository = koinInject()
) {
    val session by sessionRepository.session
        .collectAsState(initial = null)

    if (session == null) {
        AuthRoute()
    } else {
        AppNavigation()
    }
}
