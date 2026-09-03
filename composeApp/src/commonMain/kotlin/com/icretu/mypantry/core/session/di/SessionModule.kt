package com.icretu.mypantry.core.session.di

import com.icretu.mypantry.core.session.SessionRepository
import com.icretu.mypantry.core.session.SessionRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sessionModule = module{
    singleOf(::SessionRepositoryImpl).bind<SessionRepository>()
}
