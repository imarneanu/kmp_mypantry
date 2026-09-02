package com.icretu.mypantry

import android.app.Application
import com.icretu.mypantry.data.local.DatabaseSeeder
import com.icretu.mypantry.di.androidModule
import com.icretu.mypantry.core.di.commonModule
import com.icretu.mypantry.core.session.di.sessionModule
import com.icretu.mypantry.core.sync.di.syncModule
import com.icretu.mypantry.core.sync.SyncCoordinator
import com.icretu.mypantry.feature.auth.di.authModule
import com.icretu.mypantry.feature.household.di.householdModule
import com.icretu.mypantry.feature.locations.di.locationsModule
import com.icretu.mypantry.feature.pantry.di.pantryModule
import com.icretu.mypantry.feature.settings.di.settingsModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyPantryApplication : Application() {

    private val applicationScope =
        CoroutineScope(
            SupervisorJob() + Dispatchers.Default
        )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyPantryApplication)
            modules(
                commonModule,
                authModule,
                householdModule,
                sessionModule,
                pantryModule,
                locationsModule,
                settingsModule,
                syncModule,
                androidModule,
            )
        }

        applicationScope.launch {
            getKoin()
                .get<DatabaseSeeder>()
                .seedIfNeeded()
        }

        getKoin()
            .get<SyncCoordinator>()
            .start(applicationScope)
    }
}
