package com.icretu.mypantry.presentation.settings

data class SettingsState(
    val isSigningOut: Boolean = false,
    val errorMessage: String? = null
)

sealed interface SettingsIntent {
    data object InviteFamilyMemberClicked : SettingsIntent
    data object SignOutClicked : SettingsIntent
}
