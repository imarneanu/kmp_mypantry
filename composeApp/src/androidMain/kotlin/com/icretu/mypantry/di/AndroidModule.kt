package com.icretu.mypantry.di

import androidx.room.Room
import com.icretu.mypantry.data.local.DatabaseSeeder
import com.icretu.mypantry.data.local.PantryDatabase
import com.icretu.mypantry.feature.pantry.data.repository.AndroidPantryRepository
import com.icretu.mypantry.core.session.SessionRepositoryImpl
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository
import com.icretu.mypantry.core.session.SessionRepository
import com.icretu.mypantry.domain.sync.AndroidStockEntryLocalSyncDataSource
import com.icretu.mypantry.core.sync.StockEntryLocalSyncDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val androidModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PantryDatabase::class.java,
            "pantry.db"
        ).build()
    }

    single { get<PantryDatabase>().productDao() }
    single { get<PantryDatabase>().stockEntryDao() }
    single { get<PantryDatabase>().storageLocationDao() }
    single { get<PantryDatabase>().categoryDao() }

    singleOf((::DatabaseSeeder))

    singleOf(::AndroidPantryRepository).bind<PantryRepository>()
    singleOf(::AndroidStockEntryLocalSyncDataSource).bind<StockEntryLocalSyncDataSource>()
    singleOf(::SessionRepositoryImpl).bind<SessionRepository>()
}
