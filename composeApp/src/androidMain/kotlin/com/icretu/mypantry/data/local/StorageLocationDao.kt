package com.icretu.mypantry.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StorageLocationDao {
    @Query("SELECT * FROM storage_locations ORDER BY name ASC")
    fun observeLocations(): Flow<List<StorageLocationEntity>>

    @Query("SELECT COUNT(*) FROM storage_locations")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(location: StorageLocationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<StorageLocationEntity>)
}
