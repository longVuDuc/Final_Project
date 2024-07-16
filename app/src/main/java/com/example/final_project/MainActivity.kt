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
    Surface(color = MaterialTheme.colorScheme.background){
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = HomeDestination.route,
        ) {
            composable(route = LoginDestination.route){
                loginScreen(
                    navigateTohome = { navController.navigate(HomeDestination.route) },
                    navigateToSignUp = { navController.navigate(SignUpDestination.route) },
                )
            }
            composable(route = SignUpDestination.route){
                SignIn(
                    navigateToLogIn = { navController.navigate(LoginDestination.route) }
                )
            }
            composable(route = HomeDestination.route) {
                HomeScreen(
                    navigateToAddTodo = { navController.navigate(AddTodoDestination.route) },
                    navigateTohome = { navController.navigate(HomeDestination.route) },
                    navigateToSearchTodo = { navController.navigate(SearchDestination.route) },
                    navigateToProfile = { navController.navigate(ProfileDestination.route) },
                    navigateToEditTodo =  { todoId -> navController.navigate("${TodoEditDestination.route}/$todoId") }
                )
            }
            composable(route = AddTodoDestination.route) {
                AddTodoScreen(
                    navigateTohome = { navController.popBackStack()},
                )
            }
            composable(
                route = "${TodoEditDestination.route}/{${
                    TodoEditDestination.todoIdArg}}",
                arguments = listOf(navArgument(TodoEditDestination.todoIdArg) { type = NavType.IntType })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt(TodoEditDestination.todoIdArg)
                    ?: throw IllegalArgumentException("itemId not found in arguments")
                TodoEditScreen(
                    todoItemId = itemId,
                    navigateBack = { navController.popBackStack() },
                )
            }
            composable(route = SearchDestination.route) {
                SearchScreen(
                    navigateToHome = { navController.navigate(HomeDestination.route) },
                    navigateToSearchTodo = { navController.navigate(SearchDestination.route) },
                    navigateToProfile = { navController.navigate(ProfileDestination.route) },
                    navigateToEditTodo =  { todoId -> navController.navigate("${TodoEditDestination.route}/$todoId") }
                )
            }
            composable(route = ProfileDestination.route) {
                ProfileScreen(
                    navigateToLogIn = { navController.navigate(LoginDestination.route) },
                    navigateToHome = {navController.navigate(HomeDestination.route)},
                    navigateToSearch = {navController.navigate(SearchDestination.route)}
                )
            }
        }
    }
}
