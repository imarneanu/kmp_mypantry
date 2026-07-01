package com.icretu.mypantry.di

import com.icretu.mypantry.domain.usecase.AddPantryItemUseCase
import com.icretu.mypantry.domain.usecase.DeletePantryItemUseCase
import com.icretu.mypantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.domain.usecase.ObservePantryItemsUseCase
import com.icretu.mypantry.domain.usecase.UpdatePantryItemUseCase
import com.icretu.mypantry.presentation.locations.LocationsViewModel
import com.icretu.mypantry.presentation.pantry.PantryViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val commonModule = module {
    factoryOf(::ObservePantryItemsUseCase)
    factoryOf(::AddPantryItemUseCase)
    factoryOf(::UpdatePantryItemUseCase)
    factoryOf(::DeletePantryItemUseCase)
    factoryOf(::ObserveLocationsUseCase)
    factoryOf(::ObserveCategoriesUseCase)

    viewModelOf(::PantryViewModel)
    viewModelOf(::LocationsViewModel)
}
