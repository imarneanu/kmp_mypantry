package com.icretu.mypantry.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryItemDao {
    @Query("SELECT * FROM pantry_items ORDER BY name ASC")
    fun observeItems(): Flow<List<PantryItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: PantryItemEntity)

    @Query("DELETE FROM pantry_items WHERE id = :id")
    suspend fun deleteById(id: Long)
}
