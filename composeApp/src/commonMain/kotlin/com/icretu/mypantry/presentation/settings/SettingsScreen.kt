package com.icretu.mypantry.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Household",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        ListItem(
            headlineContent = {
                Text("Invite family member")
            },
            supportingContent = {
                Text("Create a code to join your household")
            },
            modifier = Modifier.clickable {
                onIntent(SettingsIntent.InviteFamilyMemberClicked)
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = "Account",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        ListItem(
            headlineContent = {
                Text("Sign out")
            },
            supportingContent = {
                Text("Sign out of MyPantry on this device")
            },
            modifier = Modifier.clickable(
                enabled = !state.isSigningOut
            ) {
                onIntent(SettingsIntent.SignOutClicked)
            }
        )

        if (state.isSigningOut) {
            CircularProgressIndicator(
                modifier = Modifier.padding(16.dp)
            )
        }

        state.errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
