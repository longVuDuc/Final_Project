package com.example.final_project.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoListViewModel
import com.example.final_project.navigation.NavigationDestination

object SearchDestination : NavigationDestination {
    override val route = "search_Screen"
    override val titleRes = R.string.search_todo_title
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    vModel: TodoListViewModel = viewModel(factory = AppViewModels.Factory),
    navigateToEditTodo: (Int) -> Unit,
    navigateToHome: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSearchTodo: () -> Unit
) {
    val query = remember { mutableStateOf("") }
    val searchResults by vModel.searchResults.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Search")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                navigateToHome = navigateToHome,
                navigateToProfile = navigateToProfile,
                navigateToSearchTodo = navigateToSearchTodo,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TextField(
                leadingIcon = { Icon(Icons.Filled.Search, tint = Color.White, contentDescription = "Search Icon") },
                value = query.value,
                onValueChange = {
                    query.value = it
                    vModel.performSearch(it)
                },
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            LazyColumn {
                items(searchResults) { item ->
                    TodoCard(todo = item, onItemClick = {
                        navigateToEditTodo(item.id)
                    })
                }
            }
        }
    }
}