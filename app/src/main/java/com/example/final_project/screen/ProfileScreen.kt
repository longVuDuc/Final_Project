package com.example.final_project.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoListViewModel
import com.example.final_project.navigation.NavigationDestination


object ProfileDestination : NavigationDestination {
    override val route = "Profile_Screen"
    override val titleRes = R.string.profile_title
}
@Composable
fun ProfileScreen(
    vModel: TodoListViewModel = viewModel(factory = AppViewModels.Factory),
    navigateToProfileUpdate: () -> Unit,
    navigateToLogIn: () -> Unit
) {
    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    navigateToLogIn()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text("Logout")
            }

            Button(
                onClick = {
                    navigateToProfileUpdate()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update Profile")
            }
        }
    }
}