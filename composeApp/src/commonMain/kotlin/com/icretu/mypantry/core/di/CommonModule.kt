package com.icretu.mypantry.core.di

import com.icretu.mypantry.core.app.AppInitializer
import com.icretu.mypantry.core.time.DefaultTimestampProvider
import com.icretu.mypantry.core.time.TimestampProvider
import com.icretu.mypantry.core.utils.IdGenerator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    singleOf(::AppInitializer)
    singleOf(::IdGenerator)
    singleOf(::DefaultTimestampProvider).bind<TimestampProvider>()
}
