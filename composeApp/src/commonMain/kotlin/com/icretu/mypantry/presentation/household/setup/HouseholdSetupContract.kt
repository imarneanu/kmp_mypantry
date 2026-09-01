package com.icretu.mypantry.presentation.household.setup

data class HouseholdSetupState(
    val householdName: String = "",
    val inviteCode: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface HouseholdSetupIntent {
    data class HouseholdNameChanged(
        val value: String
    ) : HouseholdSetupIntent

    data class InviteCodeChanged(
        val value: String
    ) : HouseholdSetupIntent

    data object CreateClicked : HouseholdSetupIntent
    data object JoinClicked : HouseholdSetupIntent
}
