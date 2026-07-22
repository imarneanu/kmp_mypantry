package com.icretu.mypantry.presentation.pantry.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.domain.model.ExpiryColor
import com.icretu.mypantry.presentation.pantry.StockEntryUiModel

@Composable
fun StockEntryCard(
    item: StockEntryUiModel,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    val color = when (item.expirationColor) {
        ExpiryColor.DEFAULT -> MaterialTheme.colorScheme.onSurfaceVariant
        ExpiryColor.WARNING -> Color(0xFFFF9800)
        ExpiryColor.ERROR -> MaterialTheme.colorScheme.error
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.productName,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(4.dp))

            Text(item.quantityText)
            Text("Location: ${item.locationName}")
            Text("Category: ${item.categoryName}")

            Spacer(Modifier.height(4.dp))

            Text(
                text = item.expirationText,
                color = color
            )

            Spacer(Modifier.height(8.dp))

            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}

