package com.icretu.mypantry.core.sync.product

import com.icretu.mypantry.feature.pantry.domain.model.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRemoteDataSource {
    private fun collection(householdId: String) =
        Firebase.firestore
            .collection("households")
            .document(householdId)
            .collection("products")

    fun observeAll(
        householdId: String,
    ): Flow<List<Product>> =
        collection(householdId)
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    Product(
                        id = document.get("id"),
                        householdId = document.get("householdId"),
                        name = document.get("name"),
                        brand = document.get<String?>("brand"),
                        categoryId = document.get("categoryId"),
                        defaultUnit = document.get("defaultUnit"),
                        updatedAtEpochMillis = document.get("updatedAtEpochMillis"),
                        updatedBy = document.get("updatedBy"),
                    )
                }
            }

    suspend fun upsert(product: Product) {
        collection(product.householdId)
            .document(product.id)
            .set(
                mapOf(
                    "id" to product.id,
                    "householdId" to product.householdId,
                    "name" to product.name,
                    "brand" to product.brand,
                    "categoryId" to product.categoryId,
                    "defaultUnit" to product.defaultUnit,
                    "updatedAtEpochMillis" to product.updatedAtEpochMillis,
                    "updatedBy" to product.updatedBy,
                )
            )
    }
}
