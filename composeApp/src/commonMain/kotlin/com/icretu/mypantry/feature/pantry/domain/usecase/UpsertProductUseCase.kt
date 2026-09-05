package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.core.time.TimestampProvider
import com.icretu.mypantry.feature.pantry.domain.model.Product
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class UpsertProductUseCase(
    private val repository: PantryRepository,
    private val timestampProvider: TimestampProvider,
) {
    suspend operator fun invoke(product: Product): String =
        repository.upsertProduct(
            product.copy(
                updatedAtEpochMillis = timestampProvider.nowEpochMillis(),
            )
        )
}
