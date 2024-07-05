package com.example.final_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                contentPadding = PaddingValues(0.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,

                    ){
                    IconButton(onClick = {navController.navigate("TodoScreen")}, modifier = Modifier.size(66.dp)) {
                        Column() {
                            Icon(Icons.Default.DateRange, contentDescription ="" , Modifier.size(32.dp))
                            Text(text = "Todo", color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    IconButton(onClick = {navController.navigate("SearchScreen")}, modifier = Modifier.size(66.dp)) {
                        Column() {
                            Icon(Icons.Default.Search, contentDescription ="" , Modifier.size(32.dp))
                            Text(text = "Search", color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.width(32.dp))
                    IconButton(onClick = {navController.navigate("SettingScreen")}, modifier = Modifier.size(66.dp)) {
                        Column() {
                            Icon(Icons.Default.Settings, contentDescription ="" , Modifier.size(32.dp))
                            Text(text = "Setting", color = Color.Black)
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate("ToDoEditScreen")  }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "TodoScreen") {
            composable("TodoScreen") { TodoScreen(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) }
            composable("SearchScreen") { SearchScreen(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize() ) }
            composable("SettingScreen") { SettingScreen(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize() ) }
            composable("ToDoEditScreen") { TodoEditScreen()}
        }
    }

}

@Composable
fun SearchScreen( modifier: Modifier = Modifier) {
    Text(text = "Search Screen")
}
@Composable
fun SettingScreen( modifier: Modifier = Modifier) {
    Text(text = "Setting Screen")
}
