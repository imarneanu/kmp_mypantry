package com.icretu.mypantry

import androidx.compose.ui.window.ComposeUIViewController
import com.icretu.mypantry.core.di.commonModule
import com.icretu.mypantry.core.session.di.sessionModule
import com.icretu.mypantry.core.sync.di.syncModule
import com.icretu.mypantry.feature.auth.di.authModule
import com.icretu.mypantry.feature.household.di.householdModule
import com.icretu.mypantry.feature.locations.di.locationsModule
import com.icretu.mypantry.feature.pantry.di.pantryModule
import com.icretu.mypantry.feature.settings.di.settingsModule
import org.koin.core.context.startKoin

object KoinIos {
    fun start() {
        startKoin {
            modules(
                commonModule,
                authModule,
                householdModule,
                sessionModule,
                pantryModule,
                locationsModule,
                settingsModule,
                syncModule,
            )
        }
    }
}

fun MainViewController() = ComposeUIViewController { App() }
