package com.icretu.mypantry.feature.household

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.icretu.mypantry.core.navigation.Route
import com.icretu.mypantry.feature.household.presentation.invite.HouseholdInviteRoute

fun NavGraphBuilder.householdGraph(
    navController: NavHostController
) {
    navigation<Route.HouseholdGraph>(
        startDestination = Route.HouseholdInvite
    ) {
        composable<Route.HouseholdInvite> {
            HouseholdInviteRoute(
                onBack = navController::popBackStack
            )
        }
    }
}
