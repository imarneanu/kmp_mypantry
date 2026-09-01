package com.icretu.mypantry.di

import com.icretu.mypantry.data.remote.FirebaseAuthRepository
import com.icretu.mypantry.data.remote.FirebaseHouseholdRepository
import com.icretu.mypantry.domain.repository.AuthRepository
import com.icretu.mypantry.domain.repository.HouseholdRepository
import com.icretu.mypantry.domain.usecase.CreateHouseholdInviteUseCase
import com.icretu.mypantry.domain.usecase.CreateHouseholdUseCase
import com.icretu.mypantry.domain.usecase.DeleteStockEntryUseCase
import com.icretu.mypantry.domain.usecase.JoinHouseholdUseCase
import com.icretu.mypantry.domain.usecase.ObserveCategoriesUseCase
import com.icretu.mypantry.domain.usecase.ObserveLocationsUseCase
import com.icretu.mypantry.domain.usecase.ObserveProductsUseCase
import com.icretu.mypantry.domain.usecase.ObserveStockEntriesUseCase
import com.icretu.mypantry.domain.usecase.SignInUseCase
import com.icretu.mypantry.domain.usecase.SignOutUseCase
import com.icretu.mypantry.domain.usecase.SignUpUseCase
import com.icretu.mypantry.domain.usecase.UpsertProductUseCase
import com.icretu.mypantry.domain.usecase.UpsertStockEntryUseCase
import com.icretu.mypantry.domain.util.DefaultTimestampProvider
import com.icretu.mypantry.domain.util.IdGenerator
import com.icretu.mypantry.domain.util.TimestampProvider
import com.icretu.mypantry.presentation.auth.AuthViewModel
import com.icretu.mypantry.presentation.household.setup.HouseholdSetupViewModel
import com.icretu.mypantry.presentation.household.invite.HouseholdInviteViewModel
import com.icretu.mypantry.presentation.locations.LocationsViewModel
import com.icretu.mypantry.presentation.pantry.PantryViewModel
import com.icretu.mypantry.presentation.settings.SettingsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    viewModelOf(::PantryViewModel)
    viewModelOf(::LocationsViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::HouseholdSetupViewModel)
    viewModelOf(::HouseholdInviteViewModel)
    viewModelOf(::SettingsViewModel)

    factoryOf(::ObserveStockEntriesUseCase)
    factoryOf(::UpsertStockEntryUseCase)
    factoryOf(::DeleteStockEntryUseCase)
    factoryOf(::ObserveProductsUseCase)
    factoryOf(::UpsertProductUseCase)
    factoryOf(::ObserveLocationsUseCase)
    factoryOf(::ObserveCategoriesUseCase)
    factoryOf(::CreateHouseholdUseCase)
    factoryOf(::JoinHouseholdUseCase)
    factoryOf(::CreateHouseholdInviteUseCase)

    factoryOf(::SignUpUseCase)
    factoryOf(::SignInUseCase)
    factoryOf(::SignOutUseCase)

    single { IdGenerator() }
    singleOf(::DefaultTimestampProvider).bind<TimestampProvider>()

    singleOf(::FirebaseAuthRepository).bind<AuthRepository>()
    singleOf(::FirebaseHouseholdRepository).bind<HouseholdRepository>()
}
