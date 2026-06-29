package com.icretu.mypantry.navigation

sealed class AppRoute(val route: String) {
    data object Pantry : AppRoute("pantry")
    data object Locations : AppRoute("locations")
    data object Categories : AppRoute("categories")
    data object ShoppingList : AppRoute("shopping_list")
    data object Settings : AppRoute("settings")
}
