package com.icretu.mypantry.core.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart

data class TopLevelDestination(
    val route: Route,
    val label: String,
    val title: String,
    val icon: ImageVector,
)

val topLevelDestinations = listOf(
    TopLevelDestination(
        route = Route.Pantry,
        label = "Pantry",
        title = "My Pantry",
        icon = Icons.Default.Home
    ),
    TopLevelDestination(
        route = Route.Locations,
        label = "Locations",
        title = "Locations",
        icon = Icons.Default.Place
    ),
    TopLevelDestination(
        route = Route.Categories,
        label = "Categories",
        title = "Categories",
        icon = Icons.Default.List
    ),
    TopLevelDestination(
        route = Route.ShoppingList,
        label = "Shopping",
        title = "Shopping List",
        icon = Icons.Default.ShoppingCart
    ),
    TopLevelDestination(
        route = Route.Settings,
        label = "Settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
)
