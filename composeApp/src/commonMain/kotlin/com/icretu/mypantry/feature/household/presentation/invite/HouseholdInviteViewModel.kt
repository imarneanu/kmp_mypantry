package com.icretu.mypantry.feature.household.presentation.invite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.feature.household.domain.usecase.CreateHouseholdInviteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HouseholdInviteViewModel(
    private val createHouseholdInviteUseCase: CreateHouseholdInviteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HouseholdInviteState())
    val state = _state.asStateFlow()

    fun createInvite() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                errorMessage = null
            )

            runCatching {
                createHouseholdInviteUseCase()
            }.onSuccess { code ->
                _state.value = _state.value.copy(
                    inviteCode = code,
                    isLoading = false
                )
            }.onFailure { error ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = error.message
                )
            }
        }
    }
}
