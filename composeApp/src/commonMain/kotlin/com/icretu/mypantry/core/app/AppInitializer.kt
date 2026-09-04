package com.icretu.mypantry.core.app

import com.icretu.mypantry.core.data.local.DatabaseSeeder
import com.icretu.mypantry.core.sync.SyncCoordinator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppInitializer(
    private val databaseSeeder: DatabaseSeeder,
    private val syncCoordinator: SyncCoordinator,
) {
    fun start(scope: CoroutineScope) {
        scope.launch {
            databaseSeeder.seedIfNeeded()
        }

        syncCoordinator.start(scope)
    }
}
