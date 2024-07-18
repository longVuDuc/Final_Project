package com.example.final_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.final_project.screen.AddTodoDestination
import com.example.final_project.screen.AddTodoScreen
import com.example.final_project.screen.HomeDestination
import com.example.final_project.screen.HomeScreen
import com.example.final_project.screen.LoginDestination
import com.example.final_project.screen.ProfileDestination
import com.example.final_project.screen.ProfileScreen
import com.example.final_project.screen.SearchDestination
import com.example.final_project.screen.SearchScreen
import com.example.final_project.screen.SignIn
import com.example.final_project.screen.SignUpDestination
import com.example.final_project.screen.TodoEditDestination
import com.example.final_project.screen.TodoEditScreen
import com.example.final_project.screen.loginScreen
import com.example.final_project.ui.theme.Final_ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Final_ProjectTheme( darkTheme = true){
                AppNavigation()
            }

        }
    }
}

@Composable
fun AppNavigation() {
    Surface(color = MaterialTheme.colorScheme.background) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = LoginDestination.route,
        ) {
            composable(route = LoginDestination.route) {
                loginScreen(
                    navigateTohome = { userId -> navController.navigate("${HomeDestination.route}/$userId") },
                    navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                )
            }
            composable(route = SignUpDestination.route) {
                SignIn(
                    navigateToLogIn = { navController.navigate(LoginDestination.route) }
                )
            }
            composable(
                route = "${HomeDestination.route}/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: throw IllegalArgumentException("userId not found in arguments")
                HomeScreen(
                    userID = userId,
                    navigateToAddTodo = { navController.navigate("${AddTodoDestination.route}/$userId") },
                    navigateTohome = { navController.navigate("${HomeDestination.route}/$userId") },
                    navigateToSearchTodo = { navController.navigate("${SearchDestination.route}/$userId") },
                    navigateToProfile = { navController.navigate("${ProfileDestination.route}/$userId") },
                    navigateToEditTodo = { todoId -> navController.navigate("${TodoEditDestination.route}/$todoId") }
                )
            }
            composable(
                route = "${AddTodoDestination.route}/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType }))
            {
                backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: throw IllegalArgumentException("userId not found in arguments")
                AddTodoScreen(
                    userID = userId,
                    navigateTohome = { navController.popBackStack() },
                )
            }
            composable(
                route = "${TodoEditDestination.route}/{${TodoEditDestination.todoIdArg}}",
                arguments = listOf(navArgument(TodoEditDestination.todoIdArg) { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt(TodoEditDestination.todoIdArg)
                    ?: throw IllegalArgumentException("itemId not found in arguments")
                TodoEditScreen(
                    todoItemId = itemId,
                    navigateBack = { navController.popBackStack() },
                )
            }
            composable(
                route = "${SearchDestination.route}/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: throw IllegalArgumentException("userId not found in arguments")
                SearchScreen(
                    userID = userId,
                    navigateToHome = { navController.navigate("${HomeDestination.route}/$userId") },
                    navigateToSearchTodo = { navController.navigate("${SearchDestination.route}/$userId") },
                    navigateToProfile = { navController.navigate("${ProfileDestination.route}/$userId") },
                    navigateToEditTodo = { todoId -> navController.navigate("${TodoEditDestination.route}/$todoId") }
                )
            }
            composable(
                route = "${ProfileDestination.route}/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getInt("userId") ?: throw IllegalArgumentException("userId not found in arguments")
                ProfileScreen(
                    userID = userId,
                    navigateToLogIn = { navController.navigate(LoginDestination.route) },
                    navigateToHome = { navController.navigate("${HomeDestination.route}/$userId") },
                    navigateToSearch = { navController.navigate("${SearchDestination.route}/$userId") }
                )
            }
        }
    }
}

