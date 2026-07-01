package com.icretu.mypantry.domain.repository

import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.model.StorageLocation
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    fun observeItems(): Flow<List<PantryItem>>

    fun observeLocations(): Flow<List<StorageLocation>>

    fun observeCategories(): Flow<List<Category>>

    suspend fun addItem(item: PantryItem)

    suspend fun deleteItem(id: Long)
}
