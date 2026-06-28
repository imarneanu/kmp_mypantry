package com.icretu.mypantry.domain.repository

import com.icretu.mypantry.domain.model.PantryItem
import kotlinx.coroutines.flow.Flow

interface PantryRepository {
    fun observeItems(): Flow<List<PantryItem>>

    suspend fun addItem(item: PantryItem)

    suspend fun deleteItem(item: PantryItem)
}
