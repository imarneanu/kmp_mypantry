package com.icretu.mypantry.di

import com.icretu.mypantry.data.remote.StockEntryRemoteDataSource
import com.icretu.mypantry.domain.sync.StockEntrySyncEngine
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::StockEntryRemoteDataSource)
    singleOf(::StockEntrySyncEngine)
}
