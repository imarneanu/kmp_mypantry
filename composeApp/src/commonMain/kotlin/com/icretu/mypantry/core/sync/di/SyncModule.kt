package com.icretu.mypantry.core.sync.di

import com.icretu.mypantry.core.sync.StockEntrySyncEngine
import com.icretu.mypantry.core.sync.SyncCoordinator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule = module {
    singleOf(::StockEntrySyncEngine)
    singleOf(::SyncCoordinator)
}
