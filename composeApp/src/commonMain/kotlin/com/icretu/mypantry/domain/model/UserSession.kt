package com.icretu.mypantry.domain.model

data class UserSession(
    val userId: String,
    val email: String?,
    val householdId: String? = null,
)
