package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.repository.PantryRepository
import com.icretu.mypantry.domain.session.UserSession
import com.icretu.mypantry.domain.util.TimestampProvider

class DeleteStockEntryUseCase(
    private val repository: PantryRepository,
    private val timestampProvider: TimestampProvider,
    private val userSession: UserSession,
) {
    suspend operator fun invoke(id: String) = repository.deleteStockEntry(
        id = id,
        updatedAtEpochMillis = timestampProvider.nowEpochMillis(),
        updatedBy = userSession.userId,
    )
}
