package com.icretu.mypantry.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.icretu.mypantry.feature.household.presentation.invite.HouseholdInviteRoute
import com.icretu.mypantry.feature.locations.presentation.LocationsRoute
import com.icretu.mypantry.feature.pantry.presentation.PantryRoute
import com.icretu.mypantry.feature.pantry.presentation.PantryViewModel
import com.icretu.mypantry.feature.pantry.presentation.stockEntry.StockEntryFormRoute
import com.icretu.mypantry.presentation.placeholder.PlaceholderScreen
import com.icretu.mypantry.feature.settings.presentation.SettingsRoute
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showAppBars = currentRoute !in setOf(
        AppRoute.StockEntryForm.route,
        AppRoute.HouseholdInvite.route
    )

    Scaffold(
        topBar = {
            if (showAppBars) {
                TopAppBar(
                    title = {
                        Text(
                            when (currentRoute) {
                                AppRoute.Pantry.route -> "My Pantry"
                                AppRoute.Locations.route -> "Locations"
                                AppRoute.Categories.route -> "Categories"
                                AppRoute.ShoppingList.route -> "Shopping List"
                                AppRoute.Settings.route -> "Settings"
                                else -> "My Pantry"
                            }
                        )
                    }
                )
            }
        },
        bottomBar = {
            if (showAppBars) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route.route,
                            onClick = {
                                navController.navigate(item.route.route) {
                                    popUpTo(AppRoute.Pantry.route) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Text(item.label.first().toString()) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.PantryGraph.route,
            modifier = Modifier.padding(padding)
        ) {
            navigation(
                route = AppRoute.PantryGraph.route,
                startDestination = AppRoute.Pantry.route
            ) {

                composable(AppRoute.HouseholdInvite.route) {
                    HouseholdInviteRoute(
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                composable(AppRoute.Pantry.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(
                            AppRoute.PantryGraph.route
                        )
                    }

                    val viewModel: PantryViewModel = koinViewModel(
                        viewModelStoreOwner = parentEntry
                    )

                    PantryRoute(
                        viewModel = viewModel,
                        onNavigateToForm = {
                            navController.navigate(
                                AppRoute.StockEntryForm.route
                            )
                        }
                    )
                }

                composable(AppRoute.StockEntryForm.route) { backStackEntry ->
                    val parentEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(
                            AppRoute.PantryGraph.route
                        )
                    }

                    val viewModel: PantryViewModel = koinViewModel(
                        viewModelStoreOwner = parentEntry
                    )

                    StockEntryFormRoute(
                        viewModel = viewModel,
                        onNavigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }

//            composable(AppRoute.Pantry.route) {
//                PantryRoute(
//                    onNavigateToForm = {
//                        navController.navigate(AppRoute.StockEntryForm.route)
//                    }
//                )
//            }
//
//            composable(AppRoute.StockEntryForm.route) {
//                StockEntryFormRoute(
//                    onNavigateBack = {
//                        navController.popBackStack()
//                    }
//                )
//            }

            composable(AppRoute.Locations.route) {
                LocationsRoute()
            }

            composable(AppRoute.Categories.route) {
                PlaceholderScreen("Categories")
            }

            composable(AppRoute.ShoppingList.route) {
                PlaceholderScreen("Shopping List")
            }

            composable(AppRoute.Settings.route) {
                SettingsRoute(
                    onInviteFamilyMember = {
                        navController.navigate(
                            AppRoute.HouseholdInvite.route
                        )
                    }
                )
            }
        }
    }
}
