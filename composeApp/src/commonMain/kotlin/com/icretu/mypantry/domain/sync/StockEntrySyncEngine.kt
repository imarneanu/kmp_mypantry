package com.icretu.mypantry.domain.sync

import com.icretu.mypantry.data.remote.StockEntryRemoteDataSource
import com.icretu.mypantry.domain.model.StockEntry
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

    private suspend fun upload(entry: StockEntry) {
        local.markSyncing(entry.id)

        runCatching {
            remote.upsert(entry)
        }.onSuccess {
            local.markSynced(entry.id)
        }.onFailure {
            local.markFailed(entry.id)
        }
    }
}
