package com.icretu.mypantry.di

import com.icretu.mypantry.core.data.local.PantryDatabase
import com.icretu.mypantry.core.data.local.buildDatabase
import com.icretu.mypantry.data.local.createIosDatabaseBuilder
import org.koin.dsl.module

val iosModule = module {
    single<PantryDatabase> {
        buildDatabase(
            createIosDatabaseBuilder()
        )
    }
}
