package com.icretu.mypantry.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    @Serializable
    data object PantryGraph : Route

    @Serializable
    data object Pantry : Route

    @Serializable
    data object StockEntryForm : Route

    @Serializable
    data object Locations : Route

    @Serializable
    data object Categories : Route

    @Serializable
    data object ShoppingList : Route

    @Serializable
    data object Settings : Route

    @Serializable
    data object HouseholdGraph : Route

    @Serializable
    data object HouseholdInvite : Route
}
