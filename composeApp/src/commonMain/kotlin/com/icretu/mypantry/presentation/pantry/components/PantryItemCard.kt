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
import androidx.compose.ui.unit.dp
import com.icretu.mypantry.domain.model.PantryItem
import com.icretu.mypantry.domain.model.ExpiryStatus
import com.icretu.mypantry.domain.model.toExpiryStatus
import com.icretu.mypantry.utils.DateUtils

@Composable
fun PantryItemCard(
    item: PantryItem,
    onDelete: () -> Unit
) {
    val expiryStatus = item.expirationDate.toExpiryStatus()
    val expiryColor = when (expiryStatus) {
        ExpiryStatus.EXPIRED -> MaterialTheme.colorScheme.error
        ExpiryStatus.EXPIRING_SOON -> MaterialTheme.colorScheme.tertiary
        ExpiryStatus.OK -> MaterialTheme.colorScheme.onSurfaceVariant
        ExpiryStatus.NONE -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(4.dp))

            Text("${item.quantity} ${item.unit}")
            Text("Location: ${item.location}")
            Text("Category: ${item.category}")

            Spacer(Modifier.height(4.dp))

            Text(
                text = when (expiryStatus) {
                    ExpiryStatus.EXPIRED ->
                        "Expired: ${DateUtils.formatDate(item.expirationDate)}"

                    ExpiryStatus.EXPIRING_SOON ->
                        "Expires soon: ${DateUtils.formatDate(item.expirationDate)}"

                    ExpiryStatus.OK ->
                        "Expires: ${DateUtils.formatDate(item.expirationDate)}"

                    ExpiryStatus.NONE ->
                        "No expiration date"
                },

                color = expiryColor

            )

            Spacer(Modifier.height(8.dp))
            Spacer(Modifier.height(8.dp))

            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}

