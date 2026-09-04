package com.icretu.mypantry

import android.app.Application
import com.icretu.mypantry.core.app.AppInitializer
import com.icretu.mypantry.core.di.initKoin
import com.icretu.mypantry.di.androidModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext

class MyPantryApplication : Application() {

    private val applicationScope =
        CoroutineScope(
            SupervisorJob() + Dispatchers.Default
        )

    override fun onCreate() {
        super.onCreate()

        val koinApplication = initKoin(
            platformModule = androidModule,
            appDeclaration = {
                androidContext(this@MyPantryApplication)
            }
        )

        koinApplication.koin
            .get<AppInitializer>()
            .start(applicationScope)
    }
}
