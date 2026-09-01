package com.icretu.mypantry.core.di

import com.icretu.mypantry.core.time.DefaultTimestampProvider
import com.icretu.mypantry.core.utils.IdGenerator
import com.icretu.mypantry.core.time.TimestampProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    single { IdGenerator() }
    singleOf(::DefaultTimestampProvider).bind<TimestampProvider>()
}
