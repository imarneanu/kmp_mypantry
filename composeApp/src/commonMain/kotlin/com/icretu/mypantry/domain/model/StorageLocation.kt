package com.icretu.mypantry.domain.model

data class StorageLocation(
    val id: String,
    val name: String,
    val type: String = "Other"
)
