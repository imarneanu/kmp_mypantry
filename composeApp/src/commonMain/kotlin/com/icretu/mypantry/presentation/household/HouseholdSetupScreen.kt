package com.icretu.mypantry.presentation.household

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HouseholdSetupScreen(
    state: HouseholdSetupState,
    onIntent: (HouseholdSetupIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Set up your household",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Create a new household or join an existing one.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Create a household",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.householdName,
            onValueChange = {
                onIntent(
                    HouseholdSetupIntent.HouseholdNameChanged(it)
                )
            },
            enabled = !state.isLoading,
            singleLine = true,
            label = {
                Text("Household name")
            },
            placeholder = {
                Text("Our Home")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                onIntent(HouseholdSetupIntent.CreateClicked)
            },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create household")
        }

        Spacer(Modifier.height(32.dp))

        HorizontalDivider()

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Join a household",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.inviteCode,
            onValueChange = {
                onIntent(
                    HouseholdSetupIntent.InviteCodeChanged(it)
                )
            },
            enabled = !state.isLoading,
            singleLine = true,
            label = {
                Text("Invite code")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                onIntent(HouseholdSetupIntent.JoinClicked)
            },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Join household")
        }

        state.errorMessage?.let { message ->
            Spacer(Modifier.height(16.dp))

            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        if (state.isLoading) {
            Spacer(Modifier.height(24.dp))

            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
