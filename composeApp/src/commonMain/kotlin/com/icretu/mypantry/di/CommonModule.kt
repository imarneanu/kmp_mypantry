package com.icretu.mypantry.di

import com.icretu.mypantry.domain.usecase.DeleteStockEntryUseCase
import com.icretu.mypantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.domain.usecase.ObserveProductsUseCase
import com.icretu.mypantry.domain.usecase.ObserveStockEntriesUseCase
import com.icretu.mypantry.domain.usecase.UpsertProductUseCase
import com.icretu.mypantry.domain.usecase.UpsertStockEntryUseCase
import com.icretu.mypantry.presentation.locations.LocationsViewModel
import com.icretu.mypantry.presentation.pantry.PantryViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::ObserveStockEntriesUseCase)
    factoryOf(::UpsertStockEntryUseCase)
    factoryOf(::DeleteStockEntryUseCase)
    factoryOf(::ObserveProductsUseCase)
    factoryOf(::UpsertProductUseCase)
    factoryOf(::ObserveLocationsUseCase)
    factoryOf(::ObserveCategoriesUseCase)

    viewModelOf(::PantryViewModel)
    viewModelOf(::LocationsViewModel)
}
