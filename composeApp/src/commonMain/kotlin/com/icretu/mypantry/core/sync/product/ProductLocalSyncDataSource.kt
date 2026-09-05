package com.icretu.mypantry.core.sync.product

import com.icretu.mypantry.feature.pantry.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductLocalSyncDataSource {
    fun observePending(
        householdId: String,
    ): Flow<List<Product>>

    suspend fun markSyncing(id: String)

    suspend fun markSynced(id: String)

    suspend fun markFailed(id: String)

    suspend fun applyRemote(product: Product)
}
