package com.icretu.mypantry.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
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

    @Upsert
    suspend fun upsert(product: ProductEntity)
}
