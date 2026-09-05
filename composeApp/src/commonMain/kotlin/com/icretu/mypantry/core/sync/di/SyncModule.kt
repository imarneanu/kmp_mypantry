package com.icretu.mypantry.core.sync.di

import com.icretu.mypantry.core.sync.stockEntry.StockEntryLocalSyncDataSource
import com.icretu.mypantry.core.sync.stockEntry.StockEntryLocalSyncDataSourceImpl
import com.icretu.mypantry.core.sync.stockEntry.StockEntrySyncEngine
import com.icretu.mypantry.core.sync.SyncCoordinator
import com.icretu.mypantry.core.sync.product.ProductLocalSyncDataSource
import com.icretu.mypantry.core.sync.product.ProductLocalSyncDataSourceImpl
import com.icretu.mypantry.core.sync.product.ProductRemoteDataSource
import com.icretu.mypantry.core.sync.product.ProductSyncEngine
import com.icretu.mypantry.core.sync.stockEntry.StockEntryRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val syncModule = module {
    singleOf(::SyncCoordinator)

    singleOf(::ProductSyncEngine)
    singleOf(::ProductLocalSyncDataSourceImpl).bind<ProductLocalSyncDataSource>()
    singleOf(::ProductRemoteDataSource)

    singleOf(::StockEntrySyncEngine)
    singleOf(::StockEntryLocalSyncDataSourceImpl).bind<StockEntryLocalSyncDataSource>()
    singleOf(::StockEntryRemoteDataSource)
}
