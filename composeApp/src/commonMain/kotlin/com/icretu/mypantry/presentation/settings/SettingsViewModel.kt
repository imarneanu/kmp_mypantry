package com.icretu.mypantry.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.usecase.SignOutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    private val _effects = MutableSharedFlow<SettingsEffect>()
    val effects = _effects.asSharedFlow()

    fun onIntent(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.InviteFamilyMemberClicked -> {
                viewModelScope.launch {
                    _effects.emit(SettingsEffect.OpenHouseholdInvite)
                }
            }

            SettingsIntent.SignOutClicked -> signOut()
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            updateState {
                copy(
                    isSigningOut = true,
                    errorMessage = null
                )
            }

            runCatching {
                signOutUseCase()
            }.onFailure { error ->
                updateState {
                    copy(
                        isSigningOut = false,
                        errorMessage = error.message
                            ?: "Could not sign out"
                    )
                }
            }
        }
    }

    private fun updateState(
        reducer: SettingsState.() -> SettingsState
    ) {
        _state.value = _state.value.reducer()
    }
}
