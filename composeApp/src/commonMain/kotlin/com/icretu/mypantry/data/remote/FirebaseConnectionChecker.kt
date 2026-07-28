package com.icretu.mypantry.data.remote

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class FirebaseConnectionChecker {

    suspend fun writeTestDocument() {
        Firebase.firestore
            .collection("connection_tests")
            .document("mypantry")
            .set(
                mapOf(
                    "message" to "Firebase connected",
                    "platform" to "Kotlin Multiplatform"
                )
            )
    }
}
