package com.icretu.mypantry.presentation.locations

import com.icretu.mypantry.domain.model.StorageLocation

data class LocationsState(
    val locations: List<StorageLocation> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

sealed interface LocationsIntent {
    data object AddClicked : LocationsIntent
    data class LocationClicked(val location: StorageLocation) : LocationsIntent
}
