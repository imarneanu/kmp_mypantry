package com.icretu.mypantry.domain.usecase

import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.repository.PantryRepository

class UpdatePantryItemUseCase(
    private val repository: PantryRepository
) {
    suspend operator fun invoke(item: PantryItem) = repository.addItem(item)
}
