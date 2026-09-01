package com.icretu.mypantry.feature.locations.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationsViewModel(
    private val observeLocationsUseCase: ObserveLocationsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LocationsState())
    val state: StateFlow<LocationsState> = _state.asStateFlow()

    init {
        observeLocations()
    }

    fun onIntent(intent: LocationsIntent) {
        when (intent) {
            LocationsIntent.AddClicked -> {
                // We'll implement adding locations next.
            }

            is LocationsIntent.LocationClicked -> {
                // Later: navigate/filter pantry by this location.
            }
        }
    }

    private fun observeLocations() {
        viewModelScope.launch {
            observeLocationsUseCase()
                .collect { locations ->
                    _state.value = LocationsState(
                        locations = locations,
                        isLoading = false
                    )
                }
        }
    }
}
