package com.icretu.mypantry.presentation.pantry

import com.icretu.mypantry.domain.model.PantryItem

data class PantryState(
    val items: List<PantryItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface PantryIntent {
//    data object LoadItems : PantryIntent
    data object AddSampleItem : PantryIntent
    data class DeleteItem(val item: PantryItem) : PantryIntent
}

sealed interface PantryEffect {
    data class ShowMessage(val message: String) : PantryEffect
}
