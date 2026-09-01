package com.icretu.mypantry.di

import com.icretu.mypantry.domain.util.DefaultTimestampProvider
import com.icretu.mypantry.domain.util.IdGenerator
import com.icretu.mypantry.domain.util.TimestampProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    single { IdGenerator() }
    singleOf(::DefaultTimestampProvider).bind<TimestampProvider>()
}
