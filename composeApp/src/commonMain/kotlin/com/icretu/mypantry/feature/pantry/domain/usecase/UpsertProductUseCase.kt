package com.icretu.mypantry.feature.pantry.domain.usecase

import com.icretu.mypantry.feature.pantry.domain.model.Product
import com.icretu.mypantry.feature.pantry.domain.repository.PantryRepository

class UpsertProductUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(product: Product): String =
        repository.upsertProduct(product)
}
