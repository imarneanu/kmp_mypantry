package com.icretu.mypantry.feature.household.domain.model

data class HouseholdMember(
    val userId: String,
    val role: HouseholdRole,
    val joinedAtEpochMillis: Long,
)
