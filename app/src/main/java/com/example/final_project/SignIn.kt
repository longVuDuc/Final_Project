package com.example.final_project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun SignIn(navController: NavHostController) {
    var (firstName, setFirstName ) = remember {
        mutableStateOf("")
    }
    var (lastName, setLastName ) = remember {
        mutableStateOf("")
    }
    var (userName, setUsername ) = remember {
        mutableStateOf("")
    }
    var (password, setPassword) = remember {
        mutableStateOf("")
    }
    var (confirmpassword, setConfirmPassword) = remember {
        mutableStateOf("")
    }
    Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign Up", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Create new account")
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = firstName,
            onValueChange = setFirstName,
            label = { Text(text = "Username" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = lastName,
            onValueChange = setLastName,
            label = { Text(text = "Username" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = userName,
            onValueChange = setUsername,
            label = { Text(text = "Username" ) },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(value = password,
            onValueChange = setPassword,
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(value = confirmpassword,
            onValueChange = setConfirmPassword,
            label = { Text(text = "Confirm Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Sign Up")
        }
        ClickableText(text = AnnotatedString("Already have an account? Login here"),
            onClick = { navController.navigate("login") })
        }
}