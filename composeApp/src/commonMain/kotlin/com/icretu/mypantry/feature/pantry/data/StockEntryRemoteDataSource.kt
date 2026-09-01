package com.icretu.mypantry.feature.pantry.data

import com.icretu.mypantry.feature.pantry.domain.model.StockEntry
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

class StockEntryRemoteDataSource {
    private fun collection(householdId: String) =
        Firebase.firestore
            .collection("households")
            .document(householdId)
            .collection("stockEntries")

    fun observeAll(
        householdId: String
    ): Flow<List<StockEntry>> =
        collection(householdId)
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    StockEntry(
                        id = document.get("id"),
                        householdId = document.get("householdId"),
                        productId = document.get("productId"),
                        locationId = document.get("locationId"),
                        quantity = document.get("quantity"),
                        unit = document.get("unit"),
                        expirationDate = document
                            .get<String?>("expirationDate")
                            ?.let(LocalDate.Companion::parse),
                        purchaseDate = document
                            .get<String?>("purchaseDate")
                            ?.let(LocalDate.Companion::parse),
                        storeName = document.get<String?>("storeName"),
                        price = document.get<Double?>("price"),
                        notes = document.get<String?>("notes"),
                        updatedAtEpochMillis = document.get("updatedAtEpochMillis"),
                        updatedBy = document.get("updatedBy"),
                        isDeleted = document.get<Boolean?>("isDeleted") ?: false
                    )
                }
            }

    suspend fun upsert(entry: StockEntry) {
        collection(entry.householdId)
            .document(entry.id)
            .set(
                mapOf(
                    "id" to entry.id,
                    "householdId" to entry.householdId,
                    "productId" to entry.productId,
                    "locationId" to entry.locationId,
                    "quantity" to entry.quantity,
                    "unit" to entry.unit,
                    "expirationDate" to entry.expirationDate?.toString(),
                    "purchaseDate" to entry.purchaseDate?.toString(),
                    "storeName" to entry.storeName,
                    "price" to entry.price,
                    "notes" to entry.notes,
                    "updatedAtEpochMillis" to entry.updatedAtEpochMillis,
                    "updatedBy" to entry.updatedBy,
                    "isDeleted" to entry.isDeleted,
                )
            )
    }

    suspend fun delete(
        householdId: String,
        entryId: String
    ) {
        collection(householdId)
            .document(entryId)
            .delete()
    }
}
