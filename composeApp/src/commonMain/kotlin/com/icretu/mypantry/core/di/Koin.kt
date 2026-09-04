package com.icretu.mypantry.core.di

import com.icretu.mypantry.core.data.di.databaseModule
import com.icretu.mypantry.core.session.di.sessionModule
import com.icretu.mypantry.core.sync.di.syncModule
import com.icretu.mypantry.feature.auth.di.authModule
import com.icretu.mypantry.feature.household.di.householdModule
import com.icretu.mypantry.feature.locations.di.locationsModule
import com.icretu.mypantry.feature.pantry.di.pantryModule
import com.icretu.mypantry.feature.settings.di.settingsModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    platformModule: Module,
    appDeclaration: KoinAppDeclaration = {},
): KoinApplication {
    return startKoin {
        appDeclaration()

        modules(
            commonModule,
            authModule,
            householdModule,
            databaseModule,
            sessionModule,
            pantryModule,
            locationsModule,
            settingsModule,
            syncModule,
            platformModule,
        )
    }
}
