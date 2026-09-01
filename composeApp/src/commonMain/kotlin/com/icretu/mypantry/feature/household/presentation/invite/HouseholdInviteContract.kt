package com.icretu.mypantry.feature.household.presentation.invite

data class HouseholdInviteState(
    val inviteCode: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
