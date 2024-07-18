package com.example.final_project.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.navigation.NavigationDestination
import com.example.final_project.user.UserViewModel


object ProfileDestination : NavigationDestination {
    override val route = "Profile_Screen"
    override val titleRes = R.string.profile_title
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateToHome: () -> Unit,
    navigateToSearch: () -> Unit,
    vModel: UserViewModel = viewModel(factory = AppViewModels.Factory),
    navigateToLogIn: () -> Unit,
    userID : Int
) {
    val userState by vModel.fetchUserById(userID).collectAsState(initial = null)
    var showProfileUpdateDialog by remember { mutableStateOf(false) }
    var showPasswordUpdateDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Profile")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                navigateToHome = { navigateToHome() },
                navigateToSearchTodo = { navigateToSearch() }) {
            }
        }
    ) { innerPadding ->
        userState?.let { user ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(text = "Firstname", color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF363636), RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Text(text = user.firstname, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Lastname", color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF363636), RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Text(text = user.lastname, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Email", color = Color.White)
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF363636), RoundedCornerShape(4.dp))
                        .padding(8.dp)
                ) {
                    Text(text = user.email, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(60.dp))
                TextButton(onClick = { showProfileUpdateDialog = true }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            tint = Color.White,
                            contentDescription = "Profile Icon"
                        )
                        Spacer(modifier = Modifier.width(8.dp))  // Adjust the spacing as needed
                        Text(
                            text = "Change profile info",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))  // Pushes the arrow icon to the end
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            tint = Color.White,
                            contentDescription = "Arrow"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { showProfileUpdateDialog = true }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            tint = Color.White,
                            contentDescription = "lock Icon"
                        )
                        Spacer(modifier = Modifier.width(8.dp))  // Adjust the spacing as needed
                        Text(
                            text = "Change account password",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))  // Pushes the arrow icon to the end
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            tint = Color.White,
                            contentDescription = "Arrow"
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                TextButton(
                    onClick = {
                        vModel.logoutUser()
                        navigateToLogIn()
                    },
                ) {
                    Text("Logout", color = Color.Red, fontSize = 20.sp)
                }
            }
        }
        if (showProfileUpdateDialog) {
            ChangeProfileDialog(
                vModel = vModel,
                onDismissRequest = { showProfileUpdateDialog = false })
        }
        if (showPasswordUpdateDialog) {
            ChangePasswordDialog(
                vModel = vModel,
                onDismissRequest = { showPasswordUpdateDialog = false })
        }
    }
}

@Composable
fun ChangeProfileDialog(
    vModel: UserViewModel,
    onDismissRequest: () -> Unit) {
    val userState by vModel.userState.collectAsState()
    var firstname by remember(userState) { mutableStateOf(userState.firstname) }
    var lastname by remember(userState) { mutableStateOf(userState.lastname) }
    var email by remember(userState) { mutableStateOf(userState.email) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp),
            color = Color(0xFF363636)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Change Profile info")
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = firstname,
                    onValueChange = { firstname = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = lastname,
                    onValueChange = { lastname = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        onDismissRequest()
                        vModel.updateProfile()
                    }) {
                        Text("Save", color = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun ChangePasswordDialog(
    vModel: UserViewModel,
    onDismissRequest: () -> Unit) {
    val userState by vModel.userState.collectAsState()
    var password by remember(userState) { mutableStateOf(userState.password) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp),
            color = Color(0xFF363636)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Change account password ")
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("password") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedButton(onClick = {
                        onDismissRequest()
                        vModel.changePassword(password)
                    }) {
                        Text("Edit", color = Color.White)
                    }
                }
            }
        }
    }
}