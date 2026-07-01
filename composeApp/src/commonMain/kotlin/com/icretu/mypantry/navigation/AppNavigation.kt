package com.icretu.mypantry.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import com.icretu.mypantry.presentation.locations.LocationsRoute
import com.icretu.mypantry.presentation.pantry.PantryRoute
import com.icretu.mypantry.presentation.placeholder.PlaceholderScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
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
        },
        bottomBar = {
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
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.Pantry.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(AppRoute.Pantry.route) {
                PantryRoute()
            }

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
                PlaceholderScreen("Settings")
            }
        }
    }
}
