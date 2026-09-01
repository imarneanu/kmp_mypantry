package com.icretu.mypantry.presentation.auth

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface AuthIntent {
    data class EmailChanged(val value: String) : AuthIntent
    data class PasswordChanged(val value: String) : AuthIntent

    data object SignInClicked : AuthIntent
    data object SignUpClicked : AuthIntent
}
