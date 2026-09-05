package com.icretu.mypantry.core.sync.product

import com.icretu.mypantry.core.sync.SyncStatus
import com.icretu.mypantry.feature.pantry.data.local.ProductDao
import com.icretu.mypantry.feature.pantry.data.local.toDomain
import com.icretu.mypantry.feature.pantry.data.local.toSyncedEntity
import com.icretu.mypantry.feature.pantry.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductLocalSyncDataSourceImpl(
    private val dao: ProductDao,
) : ProductLocalSyncDataSource {

    override fun observePending(
        householdId: String,
    ): Flow<List<Product>> =
        dao.observePending(householdId)
            .map { entities ->
                entities.map { it.toDomain() }
            }

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
        product: Product,
    ) {
        val local = dao.getById(product.id)

        when {
            local == null -> {
                dao.upsertFromRemote(
                    product.toSyncedEntity()
                )
            }

            local.syncStatus != SyncStatus.SYNCED -> {
                // Keep local pending changes.
            }

            product.updatedAtEpochMillis > local.updatedAtEpochMillis -> {
                dao.upsertFromRemote(
                    product.toSyncedEntity()
                )
            }
        }
    }
}
