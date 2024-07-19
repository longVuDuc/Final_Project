package com.example.final_project.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
object SignUpDestination : NavigationDestination {
    override val route = "SignUp"
    override val titleRes = R.string.signup
}
@Composable
fun SignIn(navigateToLogIn: () -> Unit,
    vModel: UserViewModel = viewModel(factory = AppViewModels.Factory)
) {
    val state by vModel.userState.collectAsState()
    val isFieldsNotEmpty = state.firstname.isNotEmpty()
            && state.lastname.isNotEmpty()
            && state.email.isNotEmpty()
            && state.username.isNotEmpty()
            && state.password.isNotEmpty()
    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Create new account")
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.firstname,
            onValueChange = { vModel.setFirstname(it) },
            label = { Text(text = "Fistname" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.lastname,
            onValueChange = {vModel.setLastname(it)},
            label = { Text(text = "Lastname" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.email,
            onValueChange = {vModel.setEmail(it)},
            label = { Text(text = "email" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.username,
            onValueChange = {vModel.setUsername(it)},
            label = { Text(text = "Username" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.password,
            onValueChange = {vModel.setPassword(it)},
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = {
            vModel.add()
            navigateToLogIn() }, enabled = isFieldsNotEmpty) {
            Text(text = "Sign Up")
        }
        ClickableText(text = AnnotatedString("Already have an account? Login here"),
            onClick = { navigateToLogIn() },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp, color = Color.White)
            )
        }
}