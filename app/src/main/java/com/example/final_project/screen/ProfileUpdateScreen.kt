package com.example.final_project.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.navigation.NavigationDestination
import com.example.final_project.user.UserViewModel

object ProfileUpdateDestination : NavigationDestination {
    override val route = "Profile_Update_Screen"
    override val titleRes = R.string.profile_title
}
@Composable
fun ProfileUpdateScreen(
    navigateToSetting: () -> Unit,
    vModel : UserViewModel = viewModel(factory = AppViewModels.Factory),
) {
    val UserState by vModel.state.collectAsState()
    var firstname by remember(UserState) { mutableStateOf(UserState.firstname) }
    var lastname by remember(UserState) { mutableStateOf(UserState.lastname) }
    var username by remember(UserState) { mutableStateOf(UserState.username) }
    var password by remember(UserState) { mutableStateOf(UserState.password)}
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TextField(
                value = firstname,
                onValueChange = { firstname = it },
                label = { Text("firstname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            TextField(
                value = lastname,
                onValueChange = { lastname = it },
                label = { Text("lastname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Text("Profile Update Screen")
            Button(onClick = { vModel.updateUserProfile() }) {
                Text(text = "Update")
            }
            Button(
                onClick = navigateToSetting,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Settings")
            }
        }
    }
}