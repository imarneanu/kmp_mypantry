package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.core.session.SessionRepository
import com.icretu.mypantry.core.time.TimestampProvider
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository
import kotlinx.coroutines.flow.firstOrNull

class DeleteStockEntryUseCase(
    private val repository: PantryRepository,
    private val timestampProvider: TimestampProvider,
    private val sessionRepository: SessionRepository,
) {
    suspend operator fun invoke(id: String) {
        val session = sessionRepository.session.firstOrNull()
            ?: error("No authenticated user")

        repository.deleteStockEntry(
            id = id,
            updatedAtEpochMillis = timestampProvider.nowEpochMillis(),
            updatedBy = session.userId,
        )
    }
}
