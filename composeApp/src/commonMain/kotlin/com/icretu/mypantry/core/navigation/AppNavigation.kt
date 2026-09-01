package com.icretu.mypantry.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.icretu.mypantry.feature.household.householdGraph
import com.icretu.mypantry.feature.household.presentation.invite.HouseholdInviteRoute
import com.icretu.mypantry.feature.locations.presentation.LocationsRoute
import com.icretu.mypantry.feature.pantry.pantryGraph
import com.icretu.mypantry.feature.settings.presentation.SettingsRoute
import com.icretu.mypantry.presentation.placeholder.PlaceholderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val currentTopLevelDestination =
        topLevelDestinations.firstOrNull {
            currentRoute == it.route::class.qualifiedName
        }

    Scaffold(
        topBar = {
            currentTopLevelDestination?.let {
                TopAppBar(title = { Text(currentTopLevelDestination.title) })
            }
        },
        bottomBar = {
            currentTopLevelDestination?.let {
                AppBottomNavigation(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(
                                navController.graph.startDestinationId
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Route.PantryGraph,
            modifier = Modifier.padding(padding)
        ) {
            pantryGraph(navController)

            composable<Route.Locations> {
                LocationsRoute()
            }

            composable<Route.Categories> {
                PlaceholderScreen("Categories")
            }

            composable<Route.ShoppingList> {
                PlaceholderScreen("Shopping List")
            }

            composable<Route.Settings> {
                SettingsRoute(
                    onInviteFamilyMember = {
                        navController.navigate(
                            Route.HouseholdInvite
                        )
                    }
                )
            }

            householdGraph(navController)
        }
    }
}
