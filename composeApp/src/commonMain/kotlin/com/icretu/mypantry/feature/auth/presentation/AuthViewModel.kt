package com.icretu.mypantry.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.icretu.mypantry.feature.auth.domain.usecase.SignInUseCase
import com.icretu.mypantry.feature.auth.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onIntent(intent: AuthIntent) {
        when (intent) {
            is AuthIntent.EmailChanged ->
                updateState { copy(email = intent.value) }

            is AuthIntent.PasswordChanged ->
                updateState { copy(password = intent.value) }

            AuthIntent.SignInClicked ->
                authenticate(isSignUp = false)

            AuthIntent.SignUpClicked ->
                authenticate(isSignUp = true)
        }
    }

    private fun authenticate(isSignUp: Boolean) {
        val state = _state.value

        viewModelScope.launch {
            updateState {
                copy(
                    isLoading = true,
                    errorMessage = null
                )
            }

            runCatching {
                if (isSignUp) {
                    signUpUseCase(
                        state.email.trim(),
                        state.password
                    )
                } else {
                    signInUseCase(
                        state.email.trim(),
                        state.password
                    )
                }
            }.onFailure { error ->
                updateState {
                    copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
            }.onSuccess {
                updateState {
                    copy(isLoading = false)
                }
            }
        }
    }

    private fun updateState(
        reducer: AuthState.() -> AuthState
    ) {
        _state.value = _state.value.reducer()
    }
}
