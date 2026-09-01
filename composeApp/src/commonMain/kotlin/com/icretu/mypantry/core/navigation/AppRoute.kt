package com.icretu.mypantry.core.navigation

sealed class AppRoute(val route: String) {
    data object PantryGraph : AppRoute("pantry_graph")

    data object Pantry : AppRoute("pantry")
    data object StockEntryForm : AppRoute("stock_entry_form")
    data object HouseholdInvite : AppRoute("household_invite")

    data object Locations : AppRoute("locations")
    data object Categories : AppRoute("categories")
    data object ShoppingList : AppRoute("shopping_list")
    data object Settings : AppRoute("settings")
}
