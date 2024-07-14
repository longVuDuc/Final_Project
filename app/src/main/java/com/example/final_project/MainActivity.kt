package com.example.final_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
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
import com.example.final_project.screen.SearchDestination
import com.example.final_project.screen.SearchScreen
import com.example.final_project.screen.SettingDestination
import com.example.final_project.screen.SignIn
import com.example.final_project.screen.SignUpDestination
import com.example.final_project.screen.TodoEditDestination
import com.example.final_project.screen.TodoEditScreen
import com.example.final_project.screen.loginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
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
                navigateToSetting = { navController.navigate(SettingDestination.route) },
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
                navigateToSetting = { navController.navigate(SettingDestination.route) },
                navigateToEditTodo =  { todoId -> navController.navigate("${TodoEditDestination.route}/$todoId") }
            )
        }
        composable(route = SettingDestination.route) {
            // Settings screen composable
        }
    }
}
