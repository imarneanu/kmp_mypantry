package com.icretu.mypantry.feature.pantry.di

import com.icretu.mypantry.core.sync.stockEntry.StockEntryRemoteDataSource
import com.icretu.mypantry.feature.pantry.data.repository.PantryRepositoryImpl
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository
import com.icretu.mypantry.feature.pantry.domain.usecase.DeleteStockEntryUseCase
import com.icretu.mypantry.feature.pantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.feature.pantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.feature.pantry.domain.usecase.ObserveProductsUseCase
import com.icretu.mypantry.feature.pantry.domain.usecase.ObserveStockEntriesUseCase
import com.icretu.mypantry.feature.pantry.domain.usecase.UpsertProductUseCase
import com.icretu.mypantry.feature.pantry.domain.usecase.UpsertStockEntryUseCase
import com.icretu.mypantry.feature.pantry.presentation.PantryViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pantryModule = module {
    viewModelOf(::PantryViewModel)

    singleOf(::PantryRepositoryImpl).bind<PantryRepository>()

    factoryOf(::ObserveStockEntriesUseCase)
    factoryOf(::UpsertStockEntryUseCase)
    factoryOf(::DeleteStockEntryUseCase)
    factoryOf(::ObserveProductsUseCase)
    factoryOf(::UpsertProductUseCase)
    factoryOf(::ObserveLocationsUseCase)
    factoryOf(::ObserveCategoriesUseCase)
}
