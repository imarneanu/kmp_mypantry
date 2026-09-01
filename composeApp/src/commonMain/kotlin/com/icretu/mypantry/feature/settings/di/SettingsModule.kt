package com.icretu.mypantry.feature.settings.di

import com.icretu.mypantry.feature.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}
