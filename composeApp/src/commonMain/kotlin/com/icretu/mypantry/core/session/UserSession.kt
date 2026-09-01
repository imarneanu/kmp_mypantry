package com.icretu.mypantry.core.session

data class UserSession(
    val userId: String,
    val email: String?,
    val householdId: String? = null,
)
