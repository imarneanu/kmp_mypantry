package com.icretu.mypantry.domain.model

enum class HouseholdRole {
    OWNER,
    MEMBER
}

data class HouseholdMember(
    val userId: String,
    val role: HouseholdRole,
    val joinedAtEpochMillis: Long,
)
