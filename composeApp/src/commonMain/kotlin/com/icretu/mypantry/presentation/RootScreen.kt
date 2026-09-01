package com.icretu.mypantry.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.icretu.mypantry.core.session.SessionRepository
import com.icretu.mypantry.core.navigation.AppNavigation
import com.icretu.mypantry.feature.auth.presentation.AuthRoute
import com.icretu.mypantry.feature.household.presentation.setup.HouseholdSetupRoute
import org.koin.compose.koinInject

@Composable
fun RootScreen(
    sessionRepository: SessionRepository = koinInject()
) {
    val session by sessionRepository.session
        .collectAsState(initial = null)

    when {
        session == null ->
            AuthRoute()

        session?.householdId == null ->
            HouseholdSetupRoute()

        else ->
            AppNavigation()
    }
}
