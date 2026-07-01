package com.icretu.mypantry.data.local

import com.icretu.mypantry.domain.model.Category
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.model.StorageLocation

fun PantryItemWithDetails.toDomain(): PantryItem {
    return PantryItem(
        id = id,
        name = name,
        quantity = quantity,
        unit = unit,
        locationId = locationId,
        locationName = locationName,
        categoryId = categoryId,
        categoryName = categoryName,
        expirationDate = expirationDate,
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
        locationId = locationId,
        categoryId = categoryId,
        expirationDate = expirationDate,
        storeName = storeName,
        price = price,
        notes = notes
    )
}

fun StorageLocationEntity.toDomain(): StorageLocation {
    return StorageLocation(
        id = id,
        name = name,
        type = type
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id = id,
        name = name
    )
}
