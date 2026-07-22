package com.icretu.mypantry.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StockEntryDao {

    @Query(
        """
        SELECT
            se.id,

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
            se.notes
        FROM stock_entries se
        INNER JOIN products p ON se.productId = p.id
        INNER JOIN categories c ON p.categoryId = c.id
        INNER JOIN storage_locations sl ON se.locationId = sl.id
        ORDER BY p.name ASC
        """
    )
    fun observeStockEntries(): Flow<List<StockEntryWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entry: StockEntryEntity): Long

    @Delete
    suspend fun delete(entry: StockEntryEntity)

    @Query("DELETE FROM stock_entries WHERE id = :id")
    suspend fun deleteById(id: Long)
}
