package com.icretu.mypantry

import androidx.compose.ui.window.ComposeUIViewController
import com.icretu.mypantry.core.app.AppInitializer
import com.icretu.mypantry.core.di.initKoin
import com.icretu.mypantry.di.iosModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object KoinIos {
    private val applicationScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default)

    fun start() {
        val koinApplication = initKoin(
            platformModule = iosModule
        )

        koinApplication.koin
            .get<AppInitializer>()
            .start(applicationScope)
    }
}

fun MainViewController() = ComposeUIViewController { App() }
