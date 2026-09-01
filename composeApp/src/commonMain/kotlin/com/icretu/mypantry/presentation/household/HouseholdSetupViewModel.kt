package com.icretu.mypantry.presentation.household

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.domain.usecase.CreateHouseholdUseCase
import com.icretu.mypantry.domain.usecase.JoinHouseholdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HouseholdSetupViewModel(
    private val createHouseholdUseCase: CreateHouseholdUseCase,
    private val joinHouseholdUseCase: JoinHouseholdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HouseholdSetupState())
    val state = _state.asStateFlow()

    fun onIntent(intent: HouseholdSetupIntent) {
        when (intent) {
            is HouseholdSetupIntent.HouseholdNameChanged ->
                updateState {
                    copy(
                        householdName = intent.value,
                        errorMessage = null
                    )
                }

            is HouseholdSetupIntent.InviteCodeChanged ->
                updateState {
                    copy(
                        inviteCode = intent.value,
                        errorMessage = null
                    )
                }

            HouseholdSetupIntent.CreateClicked ->
                createHousehold()

            HouseholdSetupIntent.JoinClicked ->
                joinHousehold()
        }
    }

    private fun createHousehold() {
        val name = _state.value.householdName.trim()

        if (name.isBlank()) {
            updateState {
                copy(errorMessage = "Enter a household name")
            }
            return
        }

        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                createHouseholdUseCase(name)
            }.onSuccess {
                updateState {
                    copy(isLoading = false)
                }

                // Nothing else needed here.
                // SessionRepository observes activeHouseholdId,
                // so RootScreen will automatically switch to AppNavigation.
            }.onFailure { error ->
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = error.message
                            ?: "Could not create household"
                    )
                }
            }
        }
    }

    private fun joinHousehold() {
        val code = _state.value.inviteCode.trim()

        if (code.isBlank()) {
            updateState {
                copy(errorMessage = "Enter an invite code")
            }
            return
        }

        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                joinHouseholdUseCase(code)
            }.onSuccess {
                updateState {
                    copy(isLoading = false)
                }
            }.onFailure { error ->
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = error.message
                            ?: "Could not join household"
                    )
                }
            }
        }
    }

    private fun updateState(
        reducer: HouseholdSetupState.() -> HouseholdSetupState
    ) {
        _state.value = _state.value.reducer()
    }
}
