package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.model.Product
import com.icretu.mypantry.domain.repository.PantryRepository

class UpsertProductUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(product: Product): Long =
        repository.upsertProduct(product)
}
