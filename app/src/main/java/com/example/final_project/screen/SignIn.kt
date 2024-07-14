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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val state by vModel.state.collectAsState()
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
            onValueChange = { vModel.setfirstname(it) },
            label = { Text(text = "Fistname" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.lastname,
            onValueChange = {vModel.setlastname(it)},
            label = { Text(text = "Lastname" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.username,
            onValueChange = {vModel.setusername(it)},
            label = { Text(text = "Username" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = state.password,
            onValueChange = {vModel.setpassword(it)},
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { vModel.add() }) {
            Text(text = "Sign Up")
        }
        ClickableText(text = AnnotatedString("Already have an account? Login here"),
            onClick = { navigateToLogIn() },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
            )
        }
}