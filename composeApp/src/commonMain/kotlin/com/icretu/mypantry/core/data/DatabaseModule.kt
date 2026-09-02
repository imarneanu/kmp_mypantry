package com.icretu.mypantry.core.data

import com.icretu.mypantry.core.data.local.PantryDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { get<PantryDatabase>().productDao() }
    single { get<PantryDatabase>().stockEntryDao() }
    single { get<PantryDatabase>().storageLocationDao() }
    single { get<PantryDatabase>().categoryDao() }
}
