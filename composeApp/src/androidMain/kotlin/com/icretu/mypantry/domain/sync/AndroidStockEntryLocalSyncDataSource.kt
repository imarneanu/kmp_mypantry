package com.icretu.mypantry.domain.sync

import com.icretu.mypantry.data.local.StockEntryDao
import com.icretu.mypantry.data.local.toEntity
import com.icretu.mypantry.data.local.toRecord
import com.icretu.mypantry.domain.model.StockEntryRecord
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidStockEntryLocalSyncDataSource(
    private val dao: StockEntryDao
) : StockEntryLocalSyncDataSource {

    override fun observePending(
        householdId: String
    ): Flow<List<StockEntryRecord>> =
        dao.observePending(householdId)
            .map { entities ->
                entities.map { it.toRecord() }
            }

    override suspend fun getById(
        id: String
    ): StockEntryRecord? =
        dao.getById(id)?.toRecord()

    override suspend fun markSyncing(id: String) {
        dao.updateSyncStatus(id, SyncStatus.SYNCING)
    }

    override suspend fun markSynced(id: String) {
        dao.updateSyncStatus(id, SyncStatus.SYNCED)
    }

    override suspend fun markFailed(id: String) {
        dao.updateSyncStatus(id, SyncStatus.FAILED)
    }

    override suspend fun applyRemote(
        record: StockEntryRecord
    ) {
        val local = dao.getById(record.id)
        when {
            local == null -> {
                dao.upsertFromRemote(
                    record.copy(
                        syncStatus = SyncStatus.SYNCED
                    ).toEntity()
                )
            }

            local.syncStatus != SyncStatus.SYNCED -> {
                // We currently have a local modification waiting to upload.
                // Don't overwrite it.
            }

            record.updatedAtEpochMillis > local.updatedAtEpochMillis -> {
                dao.upsertFromRemote(
                    record.copy(
                        syncStatus = SyncStatus.SYNCED
                    ).toEntity()
                )
            }
        }
    }
}
