package com.icretu.mypantry.di

import androidx.room.Room
import com.icretu.mypantry.data.local.PantryDatabase
import com.icretu.mypantry.data.repository.AndroidPantryRepository
import com.icretu.mypantry.domain.repository.PantryRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PantryDatabase::class.java,
            "pantry.db"
        ).build()
    }

    single {
        get<PantryDatabase>().pantryItemDao()
    }

    single<PantryRepository> {
        AndroidPantryRepository(
            dao = get()
        )
    }
}
