package com.icretu.mypantry.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query(
        """
        SELECT * FROM products
        ORDER BY name ASC
        """
    )
    fun observeProducts(): Flow<List<ProductEntity>>

    @Query(
        """
        SELECT * FROM products
        WHERE name = :name
        LIMIT 1
        """
    )
    suspend fun findByName(name: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductEntity): Long
}
