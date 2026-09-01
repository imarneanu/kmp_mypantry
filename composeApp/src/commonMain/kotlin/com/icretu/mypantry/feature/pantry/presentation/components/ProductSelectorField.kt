package com.icretu.mypantry.feature.pantry.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.icretu.mypantry.feature.pantry.domain.model.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSelectorField(
    value: String,
    products: List<Product>,
    expanded: Boolean,
    onValueChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onProductSelected: (String) -> Unit
) {
    val matchingProducts = products.filter {
        value.isBlank() ||
                it.name.contains(value, ignoreCase = true)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                onExpandedChange(true)
            },
            label = { Text("Product") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded && matchingProducts.isNotEmpty(),
            onDismissRequest = {
                onExpandedChange(false)
            }
        ) {
            matchingProducts.forEach { product ->
                DropdownMenuItem(
                    text = {
                        Text(
                            if (product.brand.isNullOrBlank()) {
                                product.name
                            } else {
                                "${product.name} (${product.brand})"
                            }
                        )
                    },
                    onClick = {
                        onProductSelected(product.id)
                    }
                )
            }
        }
    }
}
