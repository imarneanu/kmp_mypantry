package com.icretu.mypantry.feature.pantry.data.local

import androidx.room.*
import com.icretu.mypantry.feature.pantry.domain.model.StockEntryDetails
import com.icretu.mypantry.domain.sync.SyncStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface StockEntryDao {

    @Query(
        """
    SELECT
        se.id,
        se.householdId,

        p.id AS productId,
        p.name AS productName,
        p.brand AS productBrand,

        c.id AS categoryId,
        c.name AS categoryName,

        se.quantity,
        se.unit,

        sl.id AS locationId,
        sl.name AS locationName,

        se.expirationDate,
        se.purchaseDate,
        se.storeName,
        se.price,
        se.notes,

        se.updatedAtEpochMillis,
        se.updatedBy

    FROM stock_entries se

    INNER JOIN products p
        ON se.productId = p.id

    INNER JOIN categories c
        ON p.categoryId = c.id

    INNER JOIN storage_locations sl
        ON se.locationId = sl.id

    WHERE se.isDeleted = 0

    ORDER BY p.name ASC
    """
    )
    fun observeStockEntries(): Flow<List<StockEntryDetails>>

    @Upsert
    suspend fun upsert(entry: StockEntryEntity)

    @Delete
    suspend fun delete(entry: StockEntryEntity)

    @Query("DELETE FROM stock_entries WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query(
        """
    UPDATE stock_entries
    SET isDeleted = 1,
        syncStatus = 'PENDING',
        updatedAtEpochMillis = :updatedAtEpochMillis,
        updatedBy = :updatedBy
    WHERE id = :id
    """
    )
    suspend fun markDeleted(
        id: String,
        updatedAtEpochMillis: Long,
        updatedBy: String
    )

    @Query(
        """
    SELECT * FROM stock_entries
    WHERE householdId = :householdId
      AND syncStatus IN ('PENDING', 'FAILED')
    ORDER BY updatedAtEpochMillis ASC
    """
    )
    fun observePending(
        householdId: String
    ): Flow<List<StockEntryEntity>>

    @Query(
        """
    UPDATE stock_entries
    SET syncStatus = :status
    WHERE id = :id
    """
    )
    suspend fun updateSyncStatus(
        id: String,
        status: SyncStatus
    )

    @Upsert
    suspend fun upsertFromRemote(entity: StockEntryEntity)

    @Query("SELECT * FROM stock_entries WHERE id = :id")
    suspend fun getById(id: String): StockEntryEntity?
}
