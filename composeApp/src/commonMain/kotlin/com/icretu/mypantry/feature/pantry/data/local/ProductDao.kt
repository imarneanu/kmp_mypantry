package com.icretu.mypantry.feature.pantry.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.icretu.mypantry.core.sync.SyncStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query(
        """
        SELECT * FROM products
        WHERE householdId = :householdId
        ORDER BY name ASC
        """
    )
    fun observeProducts(householdId: String): Flow<List<ProductEntity>>

    @Query(
        """
    SELECT * FROM products
    WHERE householdId = :householdId
      AND syncStatus IN ('PENDING', 'FAILED')
    ORDER BY updatedAtEpochMillis ASC
    """
    )
    fun observePending(
        householdId: String
    ): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getById(id: String): ProductEntity?

    @Upsert
    suspend fun upsertFromRemote(entity: ProductEntity)

    @Query(
        """
    UPDATE products
    SET syncStatus = :status
    WHERE id = :id
    """
    )
    suspend fun updateSyncStatus(
        id: String,
        status: SyncStatus
    )

    @Query(
        """
        SELECT * FROM products
        WHERE name = :name
        LIMIT 1
        """
    )
    suspend fun findByName(name: String): ProductEntity?

    @Upsert
    suspend fun upsert(product: ProductEntity)
}
