package com.icretu.mypantry.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppBottomNavigation(
    currentRoute: String?,
    onNavigate: (Route) -> Unit
) {
    NavigationBar {
        topLevelDestinations.forEach { destination ->
            NavigationBarItem(
                selected = currentRoute ==
                        destination.route::class.qualifiedName,
                onClick = {
                    onNavigate(destination.route)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                label = {
                    Text(destination.label)
                }
            )
        }
    }
}
