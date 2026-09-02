package com.icretu.mypantry.core.sync

import com.icretu.mypantry.feature.pantry.data.local.StockEntryDao
import com.icretu.mypantry.feature.pantry.data.local.toDomain
import com.icretu.mypantry.feature.pantry.data.local.toSyncedEntity
import com.icretu.mypantry.feature.pantry.domain.model.StockEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StockEntryLocalSyncDataSourceImpl(
    private val dao: StockEntryDao
) : StockEntryLocalSyncDataSource {

    override fun observePending(
        householdId: String
    ): Flow<List<StockEntry>> =
        dao.observePending(householdId)
            .map { entities ->
                entities.map { it.toDomain() }
            }

    override suspend fun getById(
        id: String
    ): StockEntry? =
        dao.getById(id)?.toDomain()

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
        entry: StockEntry
    ) {
        val local = dao.getById(entry.id)
        when {
            local == null -> {
                dao.upsertFromRemote(
                    entry.toSyncedEntity()
                )
            }

            local.syncStatus != SyncStatus.SYNCED -> {
                // We currently have a local modification waiting to upload.
                // Don't overwrite it.
            }

            entry.updatedAtEpochMillis > local.updatedAtEpochMillis -> {
                dao.upsertFromRemote(
                    entry.toSyncedEntity()
                )
            }
        }
    }
}
