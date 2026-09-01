package com.icretu.mypantry.core.sync

import com.icretu.mypantry.core.session.SessionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SyncCoordinator(
    private val sessionRepository: SessionRepository,
    private val stockEntrySyncEngine: StockEntrySyncEngine
) {
    private var sessionJob: Job? = null

    fun start(scope: CoroutineScope) {
        if (sessionJob != null) return

        sessionJob = scope.launch {
            sessionRepository.session.collectLatest { session ->
                val householdId = session?.householdId

                if (householdId == null) {
                    stockEntrySyncEngine.stop()
                } else {
                    stockEntrySyncEngine.start(
                        scope = this,
                        householdId = householdId
                    )
                }
            }
        }
    }

    fun stop() {
        sessionJob?.cancel()
        sessionJob = null

        stockEntrySyncEngine.stop()
    }
}
