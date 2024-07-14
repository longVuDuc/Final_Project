package com.example.final_project.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.navigation.NavigationDestination
import com.example.final_project.user.UserViewModel

object LoginDestination : NavigationDestination {
    override val route = "Login"
    override val titleRes = R.string.login
}
@Composable
fun loginScreen(navigateTohome : () -> Unit,
                navigateToSignUp : () -> Unit,
                vModel: UserViewModel = viewModel(factory = AppViewModels.Factory),
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(top = 20.dp)
        )
        Text(text = "Welcome back", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Login to your account", fontSize = 16.sp)
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(text = "Username") },
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            vModel.validateUserCredentials(
                username = userName,
                password = password,
                onSuccess = {
                    // Navigate to home screen
                    navigateTohome()
                },
                onError = {
                    showErrorDialog = true
                }
            )
        }) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(text = AnnotatedString("Does not have an account? Sign up here"),
            onClick = { navigateToSignUp() },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
            )
    }
    // Display error dialog if showErrorDialog is true
    if (showErrorDialog) {
        ErrorDialog(onDismiss = { showErrorDialog = false })
    }

}


@Composable
fun ErrorDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Login Failed") },
        text = { Text("Invalid username or password. Please try again.") },
        confirmButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text("OK")
            }
        }
    )
}
