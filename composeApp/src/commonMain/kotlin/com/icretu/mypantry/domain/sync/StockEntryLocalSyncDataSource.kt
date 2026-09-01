package com.icretu.mypantry.domain.sync

import com.icretu.mypantry.domain.model.StockEntry
import kotlinx.coroutines.flow.Flow

interface StockEntryLocalSyncDataSource {

    fun observePending(
        householdId: String
    ): Flow<List<StockEntry>>

    suspend fun markSyncing(id: String)

    suspend fun markSynced(id: String)

    suspend fun markFailed(id: String)

    suspend fun getById(id: String): StockEntry?

    suspend fun applyRemote(entry: StockEntry)
}
