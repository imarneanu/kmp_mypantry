package com.icretu.mypantry.domain.model

data class Household(
    val id: String,
    val name: String,
    val createdBy: String,
    val createdAtEpochMillis: Long
)
