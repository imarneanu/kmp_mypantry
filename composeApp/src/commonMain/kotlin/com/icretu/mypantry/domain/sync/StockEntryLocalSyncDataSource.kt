package com.icretu.mypantry.domain.sync

import com.icretu.mypantry.domain.model.StockEntryRecord
import kotlinx.coroutines.flow.Flow

interface StockEntryLocalSyncDataSource {

    fun observePending(
        householdId: String
    ): Flow<List<StockEntryRecord>>

    suspend fun markSyncing(id: String)

    suspend fun markSynced(id: String)

    suspend fun markFailed(id: String)

    suspend fun getById(id: String): StockEntryRecord?

    suspend fun applyRemote(record: StockEntryRecord)
}
