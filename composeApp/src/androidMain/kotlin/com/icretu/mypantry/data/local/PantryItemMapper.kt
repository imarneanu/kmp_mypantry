package com.icretu.mypantry.data.local

import com.icretu.mypantry.domain.model.PantryItem

fun PantryItemEntity.toDomain(): PantryItem {
    return PantryItem(
        id = id,
        name = name,
        quantity = quantity,
        unit = unit,
        location = location,
        category = category,
        expirationDateMillis = expirationDateMillis,
        storeName = storeName,
        price = price,
        notes = notes
    )
}

fun PantryItem.toEntity(): PantryItemEntity {
    return PantryItemEntity(
        id = id,
        name = name,
        quantity = quantity,
        unit = unit,
        location = location,
        category = category,
        expirationDateMillis = expirationDateMillis,
        storeName = storeName,
        price = price,
        notes = notes
    )
}
