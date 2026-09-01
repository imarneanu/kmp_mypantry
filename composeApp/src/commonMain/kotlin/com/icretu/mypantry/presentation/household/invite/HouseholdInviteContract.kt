package com.icretu.mypantry.presentation.household.invite

data class HouseholdInviteState(
    val inviteCode: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
