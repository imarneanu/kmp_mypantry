package com.icretu.mypantry.di

import com.icretu.mypantry.core.data.local.PantryDatabase
import com.icretu.mypantry.core.data.local.buildDatabase
import com.icretu.mypantry.data.local.createAndroidDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<PantryDatabase> {
        buildDatabase(
            createAndroidDatabaseBuilder(androidContext())
        )
    }
}
