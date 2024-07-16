package com.example.final_project.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoListUiState
import com.example.final_project.TodoList.TodoListViewModel
import com.example.final_project.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateTohome: () -> Unit,
    navigateToAddTodo : () -> Unit,
    navigateToEditTodo : (Int) -> Unit,
    navigateToSearchTodo : () -> Unit,
    navigateToProfile : () -> Unit,
    vModel: TodoListViewModel = viewModel(factory = AppViewModels.Factory),
) {
    val state by vModel.TodolistUiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Home")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                navigateToHome = navigateTohome,
                navigateToProfile = navigateToProfile,
                navigateToSearchTodo = navigateToSearchTodo,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToAddTodo()},
                shape = CircleShape,
                modifier = Modifier.padding(10.dp).size(80.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_todo_title))
            }
        }
    ) { innerPadding ->
        TodoScreen(state = state, onItemClick = navigateToEditTodo, modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoScreen(state: TodoListUiState, onItemClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    if (state.todoList.isEmpty()) {
        EmptyState()
    } else {
        val groupedTodos = remember(state.todoList) { state.todoList.groupBy { it.status } }
        LazyColumn(modifier = modifier.fillMaxSize()) {
            groupedTodos.forEach { (status, todos) ->
                stickyHeader {
                    Text(
                        text = status.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 16.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White // Adjust text color for better visibility on black background
                    )
                }
                items(todos, key = { it.id }) { todo ->
                    TodoCard(todo = todo, onItemClick = onItemClick)
                }
            }
        }
    }
}
@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "What do you want to do today?")
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = "Tap + to add your task")
    }
}

