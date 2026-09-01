package com.icretu.mypantry.feature.settings.presentation

data class SettingsState(
    val isSigningOut: Boolean = false,
    val errorMessage: String? = null
)

sealed interface SettingsIntent {
    data object InviteFamilyMemberClicked : SettingsIntent
    data object SignOutClicked : SettingsIntent
}
