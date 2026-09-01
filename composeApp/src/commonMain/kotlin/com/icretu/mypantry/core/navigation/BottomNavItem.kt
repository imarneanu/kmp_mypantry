package com.icretu.mypantry.core.navigation

data class BottomNavItem(
    val route: AppRoute,
    val label: String
)

val bottomNavItems = listOf(
    BottomNavItem(AppRoute.Pantry, "Pantry"),
    BottomNavItem(AppRoute.Locations, "Locations"),
    BottomNavItem(AppRoute.Categories, "Categories"),
    BottomNavItem(AppRoute.ShoppingList, "Shopping"),
    BottomNavItem(AppRoute.Settings, "Settings")
)
