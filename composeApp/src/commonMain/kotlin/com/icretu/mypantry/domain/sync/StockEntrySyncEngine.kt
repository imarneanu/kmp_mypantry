package com.icretu.mypantry.domain.sync

import com.icretu.mypantry.data.remote.StockEntryRemoteDataSource
import com.icretu.mypantry.domain.model.StockEntryRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StockEntrySyncEngine(
    private val local: StockEntryLocalSyncDataSource,
    private val remote: StockEntryRemoteDataSource,
) {
    private var uploadJob: Job? = null
    private var downloadJob: Job? = null

    fun start(
        scope: CoroutineScope,
        householdId: String
    ) {
        stop()

        uploadJob = scope.launch {
            local.observePending(householdId)
                .collect { records ->
                    records.forEach { record ->
                        upload(record)
                    }
                }
        }

        downloadJob = scope.launch {
            remote.observeAll(householdId)
                .collect { records ->
                    records.forEach { record ->
                        local.applyRemote(record)
                    }
                }
        }
    }

    fun stop() {
        uploadJob?.cancel()
        downloadJob?.cancel()

        uploadJob = null
        downloadJob = null
    }

    private suspend fun upload(record: StockEntryRecord) {
        local.markSyncing(record.id)

        runCatching {
            remote.upsert(record)
        }.onSuccess {
            local.markSynced(record.id)
        }.onFailure {
            local.markFailed(record.id)
        }
    }
}
