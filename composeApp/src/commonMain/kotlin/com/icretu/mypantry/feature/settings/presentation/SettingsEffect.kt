package com.icretu.mypantry.feature.settings.presentation

sealed interface SettingsEffect {
    data object OpenHouseholdInvite : SettingsEffect
}
