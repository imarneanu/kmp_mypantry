package com.icretu.mypantry.feature.locations.di

import com.icretu.mypantry.feature.locations.presentation.LocationsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val locationsModule = module {
    viewModelOf(::LocationsViewModel)
}
