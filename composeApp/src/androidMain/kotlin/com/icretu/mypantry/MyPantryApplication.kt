package com.icretu.mypantry

import android.app.Application
import com.icretu.mypantry.data.local.DatabaseSeeder
import com.icretu.mypantry.di.androidModule
import com.icretu.mypantry.di.commonModule
import com.icretu.mypantry.di.databaseModule
import com.icretu.mypantry.domain.sync.SyncCoordinator
import com.icretu.mypantry.feature.auth.di.authModule
import com.icretu.mypantry.feature.household.di.householdModule
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
                androidModule,
                databaseModule,
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
