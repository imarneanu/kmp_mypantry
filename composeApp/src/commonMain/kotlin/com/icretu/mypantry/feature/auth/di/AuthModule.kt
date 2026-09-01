package com.icretu.mypantry.feature.auth.di

import com.icretu.mypantry.feature.auth.data.FirebaseAuthRepository
import com.icretu.mypantry.feature.auth.domain.repository.AuthRepository
import com.icretu.mypantry.feature.auth.domain.usecase.SignInUseCase
import com.icretu.mypantry.feature.auth.domain.usecase.SignOutUseCase
import com.icretu.mypantry.feature.auth.domain.usecase.SignUpUseCase
import com.icretu.mypantry.feature.auth.presentation.AuthViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    viewModelOf(::AuthViewModel)

    singleOf(::FirebaseAuthRepository).bind<AuthRepository>()

    factoryOf(::SignUpUseCase)
    factoryOf(::SignInUseCase)
    factoryOf(::SignOutUseCase)
}
