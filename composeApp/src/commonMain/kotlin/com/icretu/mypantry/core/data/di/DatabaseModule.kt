package com.icretu.mypantry.core.data.di

import com.icretu.mypantry.core.data.local.DatabaseSeeder
import com.icretu.mypantry.core.data.local.PantryDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single { get<PantryDatabase>().productDao() }
    single { get<PantryDatabase>().stockEntryDao() }
    single { get<PantryDatabase>().storageLocationDao() }
    single { get<PantryDatabase>().categoryDao() }

    singleOf((::DatabaseSeeder))
}
