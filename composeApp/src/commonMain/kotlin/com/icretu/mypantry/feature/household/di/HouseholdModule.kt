package com.icretu.mypantry.feature.household.di

import com.icretu.mypantry.feature.household.data.FirebaseHouseholdRepository
import com.icretu.mypantry.feature.household.domain.repository.HouseholdRepository
import com.icretu.mypantry.feature.household.domain.usecase.CreateHouseholdInviteUseCase
import com.icretu.mypantry.feature.household.domain.usecase.CreateHouseholdUseCase
import com.icretu.mypantry.feature.household.domain.usecase.JoinHouseholdUseCase
import com.icretu.mypantry.feature.household.presentation.invite.HouseholdInviteViewModel
import com.icretu.mypantry.feature.household.presentation.setup.HouseholdSetupViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val householdModule = module {
    viewModelOf(::HouseholdSetupViewModel)
    viewModelOf(::HouseholdInviteViewModel)

    singleOf(::FirebaseHouseholdRepository).bind<HouseholdRepository>()

    factoryOf(::CreateHouseholdUseCase)
    factoryOf(::JoinHouseholdUseCase)
    factoryOf(::CreateHouseholdInviteUseCase)
}
