package com.icretu.mypantry.data.repository

import com.icretu.mypantry.data.local.PantryItemDao
import com.icretu.mypantry.data.local.toDomain
import com.icretu.mypantry.data.local.toEntity
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.repository.PantryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidPantryRepository(
    private val dao: PantryItemDao
) : PantryRepository {

    override fun observeItems(): Flow<List<PantryItem>> {
        return dao.observeItems()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    override suspend fun addItem(item: PantryItem) {
        dao.upsertItem(item.toEntity())
    }

    override suspend fun deleteItem(item: PantryItem) {
        dao.deleteItem(item.toEntity())
    }
}
