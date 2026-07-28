package com.icretu.mypantry

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.icretu.mypantry.data.local.DatabaseSeeder
import com.icretu.mypantry.data.remote.FirebaseConnectionChecker
import com.icretu.mypantry.di.androidModule
import com.icretu.mypantry.di.commonModule
import com.icretu.mypantry.di.databaseModule
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.getKoin


private const val FIREBASE_TAG = "FirebaseConnection"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(
                commonModule,
                androidModule,
                databaseModule,
            )
        }

        lifecycleScope.launch {
            getKoin().get<DatabaseSeeder>().seedIfNeeded()
        }

        lifecycleScope.launch {
            runCatching {
                getKoin()
                    .get<FirebaseConnectionChecker>()
                    .writeTestDocument()
            }.onSuccess {
                Log.d(
                    FIREBASE_TAG,
                    "Firestore test document written successfully"
                )
            }.onFailure { error ->
                Log.e(
                    FIREBASE_TAG,
                    "Firestore test document write failed",
                    error
                )
            }
        }

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
