package com.icretu.mypantry.di

import com.icretu.mypantry.domain.usecase.DeleteStockEntryUseCase
import com.icretu.mypantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.domain.usecase.ObserveProductsUseCase
import com.icretu.mypantry.domain.usecase.ObserveStockEntriesUseCase
import com.icretu.mypantry.domain.usecase.UpsertProductUseCase
import com.icretu.mypantry.domain.usecase.UpsertStockEntryUseCase
import com.icretu.mypantry.domain.util.DefaultTimestampProvider
import com.icretu.mypantry.domain.util.IdGenerator
import com.icretu.mypantry.domain.util.TimestampProvider
import com.icretu.mypantry.presentation.locations.LocationsViewModel
import com.icretu.mypantry.presentation.pantry.PantryViewModel
import com.icretu.mypantry.presentation.settings.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    viewModelOf(::PantryViewModel)
    viewModelOf(::LocationsViewModel)
    viewModelOf(::SettingsViewModel)

    factoryOf(::ObserveStockEntriesUseCase)
    factoryOf(::UpsertStockEntryUseCase)
    factoryOf(::DeleteStockEntryUseCase)
    factoryOf(::ObserveProductsUseCase)
    factoryOf(::UpsertProductUseCase)
    factoryOf(::ObserveLocationsUseCase)
    factoryOf(::ObserveCategoriesUseCase)

    single { IdGenerator() }
    singleOf(::DefaultTimestampProvider).bind<TimestampProvider>()

}
