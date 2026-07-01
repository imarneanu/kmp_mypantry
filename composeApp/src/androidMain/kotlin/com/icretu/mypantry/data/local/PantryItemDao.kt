package com.icretu.mypantry.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryItemDao {
    @Query(
        """
        SELECT 
            pi.id,
            pi.name,
            pi.quantity,
            pi.unit,
            pi.locationId,
            sl.name AS locationName,
            pi.categoryId,
            c.name AS categoryName,
            pi.expirationDate,
            pi.storeName,
            pi.price,
            pi.notes
        FROM pantry_items pi
        INNER JOIN storage_locations sl ON pi.locationId = sl.id
        INNER JOIN categories c ON pi.categoryId = c.id
        ORDER BY pi.name ASC
        """
    )
    fun observeItems(): Flow<List<PantryItemWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: PantryItemEntity)

    @Query("DELETE FROM pantry_items WHERE id = :id")
    suspend fun deleteById(id: Long)
}
