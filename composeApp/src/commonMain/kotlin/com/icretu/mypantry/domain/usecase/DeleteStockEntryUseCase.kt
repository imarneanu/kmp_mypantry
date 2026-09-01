package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.PantryRepository
import com.icretu.mypantry.domain.repository.SessionRepository
import com.icretu.mypantry.domain.util.TimestampProvider
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
