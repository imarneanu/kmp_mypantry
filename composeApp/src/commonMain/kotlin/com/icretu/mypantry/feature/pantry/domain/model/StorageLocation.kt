package com.icretu.mypantry.feature.pantry.domain.model

data class StorageLocation(
    val id: String,
    val name: String,
    val type: String = "Other",
)
