package com.icretu.mypantry.presentation.settings

sealed interface SettingsEffect {
    data object OpenHouseholdInvite : SettingsEffect
}
