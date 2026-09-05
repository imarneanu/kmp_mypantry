package com.icretu.mypantry.core.sync.stockEntry

import com.icretu.mypantry.feature.pantry.domain.model.StockEntry
import kotlinx.coroutines.flow.Flow

interface StockEntryLocalSyncDataSource {

    fun observePending(
        householdId: String
    ): Flow<List<StockEntry>>

    suspend fun markSyncing(id: String)

    suspend fun markSynced(id: String)

    suspend fun markFailed(id: String)

    suspend fun applyRemote(entry: StockEntry)
}
