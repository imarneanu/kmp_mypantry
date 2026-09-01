package com.icretu.mypantry.data.remote

import com.icretu.mypantry.domain.model.StockEntry
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow

class StockEntryRemoteDataSource {
    private fun collection(householdId: String) =
        Firebase.firestore
            .collection("households")
            .document(householdId)
            .collection("stockEntries")

    fun observeAll(
        householdId: String
    ): Flow<List<StockEntry>> {
        TODO("Step 5")
    }
//        collection(householdId)
//            .snapshots
//            .map { snapshot ->
//                snapshot.documents.map { document ->
//                    document.data<RemoteStockEntry>()
//                        .toRecord()
//                }
//            }

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
