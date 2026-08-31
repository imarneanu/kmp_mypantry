package com.icretu.mypantry.data.remote

import com.icretu.mypantry.data.remote.model.RemoteStockEntry
import com.icretu.mypantry.data.remote.model.toRecord
import com.icretu.mypantry.domain.model.StockEntryRecord
import com.icretu.mypantry.domain.model.toRemote
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StockEntryRemoteDataSource {
    private fun collection(householdId: String) =
        Firebase.firestore
            .collection("households")
            .document(householdId)
            .collection("stockEntries")

    fun observeAll(
        householdId: String
    ): Flow<List<StockEntryRecord>> =
        collection(householdId)
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    document.data<RemoteStockEntry>()
                        .toRecord()
                }
            }

    suspend fun upsert(record: StockEntryRecord) {
        collection(record.householdId)
            .document(record.id)
            .set(record.toRemote())
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
