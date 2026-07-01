package com.icretu.mypantry.domain.model

data class StorageLocation(
    val id: Long = 0,
    val name: String,
    val type: String = "Other"
)
