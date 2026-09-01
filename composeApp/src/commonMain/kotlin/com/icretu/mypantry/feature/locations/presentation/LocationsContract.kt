package com.icretu.mypantry.feature.locations.presentation

import com.icretu.mypantry.feature.pantry.domain.model.StorageLocation

data class LocationsState(
    val locations: List<StorageLocation> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

sealed interface LocationsIntent {
    data object AddClicked : LocationsIntent
    data class LocationClicked(val location: StorageLocation) : LocationsIntent
}
