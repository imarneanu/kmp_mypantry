package com.icretu.mypantry.feature.pantry

import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.icretu.mypantry.core.navigation.Route
import com.icretu.mypantry.feature.pantry.presentation.PantryRoute
import com.icretu.mypantry.feature.pantry.presentation.PantryViewModel
import com.icretu.mypantry.feature.pantry.presentation.stockEntry.StockEntryFormRoute
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.pantryGraph(
    navController: NavHostController
) {
    navigation<Route.PantryGraph>(
        startDestination = Route.Pantry
    ) {
        composable<Route.Pantry> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Route.PantryGraph>()
            }

            val viewModel: PantryViewModel = koinViewModel(
                viewModelStoreOwner = parentEntry
            )

            PantryRoute(
                viewModel = viewModel,
                onNavigateToForm = {
                    navController.navigate(Route.StockEntryForm)
                }
            )
        }

        composable<Route.StockEntryForm> { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry<Route.PantryGraph>()
            }

            val viewModel: PantryViewModel = koinViewModel(
                viewModelStoreOwner = parentEntry
            )

            StockEntryFormRoute(
                viewModel = viewModel,
                onNavigateBack = navController::navigateUp
            )
        }
    }
}


//composable<Route.Pantry> { backStackEntry ->
//    val parentEntry = remember(backStackEntry) {
//        navController.getBackStackEntry(
//            Route.PantryGraph
//        )
//    }
//
//    val viewModel: PantryViewModel = koinViewModel(
//        viewModelStoreOwner = parentEntry
//    )
//
//    PantryRoute(
//        viewModel = viewModel,
//        onNavigateToForm = {
//            navController.navigate(
//                Route.StockEntryForm
//            )
//        }
//    )
//}
//
//composable<Route.StockEntryForm> { backStackEntry ->
//    val parentEntry = remember(backStackEntry) {
//        navController.getBackStackEntry(
//            Route.PantryGraph
//        )
//    }
//
//    val viewModel: PantryViewModel = koinViewModel(
//        viewModelStoreOwner = parentEntry
//    )
//
//    StockEntryFormRoute(
//        viewModel = viewModel,
//        onNavigateBack = {
//            navController.popBackStack()
//        }
//    )
//}
//}
