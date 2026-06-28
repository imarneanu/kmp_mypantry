package com.icretu.mypantry

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.icretu.mypantry.presentation.pantry.PantryRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        PantryRoute()
    }
}
