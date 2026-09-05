package com.icretu.mypantry.core.sync.product

import com.icretu.mypantry.feature.pantry.data.local.ProductEntity
import com.icretu.mypantry.feature.pantry.domain.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductSyncEngine(
    private val local: ProductLocalSyncDataSource,
    private val remote: ProductRemoteDataSource,
) {
    private var uploadJob: Job? = null
    private var downloadJob: Job? = null

    fun start(
        scope: CoroutineScope,
        householdId: String,
    ) {
        stop()

        uploadJob = scope.launch {
            local.observePending(householdId)
                .collect { products ->
                    products.forEach { product ->
                        upload(product)
                    }
                }
        }

        downloadJob = scope.launch {
            remote.observeAll(householdId)
                .collect { products ->
                    products.forEach { product ->
                        local.applyRemote(product)
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

    private suspend fun upload(entry: Product) {
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
