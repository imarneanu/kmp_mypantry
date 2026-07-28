package com.icretu.mypantry.di

import com.icretu.mypantry.data.remote.FirebaseConnectionChecker
import org.koin.dsl.module

val databaseModule = module {
    single { FirebaseConnectionChecker() }
}
