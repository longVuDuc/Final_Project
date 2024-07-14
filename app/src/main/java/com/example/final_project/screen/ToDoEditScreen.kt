package com.example.final_project.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoDetailViewModel
import com.example.final_project.TodoList.UiState
import com.example.final_project.navigation.NavigationDestination

object TodoEditDestination : NavigationDestination {
    override val route = "todo_edit"
    override val titleRes = R.string.edit_todo_title
    const val todoIdArg = "todoId"
    val routeWithArgs = "$route/{$todoIdArg}"
}

@Composable
fun TodoEditScreen(
    todoItemId: Int,
    navigateBack: () -> Unit,
    vModel: TodoDetailViewModel = viewModel(factory = AppViewModels.Factory)
) {
    // Fetch the to-do item
    LaunchedEffect(todoItemId) {
        vModel.getTodoItemByID(todoItemId)
    }
    val todoState by vModel.state.collectAsState()

    var todoTitle by remember(todoState) { mutableStateOf(todoState.name) }
    var todoDescription by remember(todoState) { mutableStateOf(todoState.description) }
    var todotime by remember(todoState) { mutableStateOf(todoState.time) }
    var tododate by remember(todoState) { mutableStateOf(todoState.date) }
    var showErrorDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = todoTitle,
            onValueChange = { todoTitle = it },
            label = { Text("Title") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = todoDescription,
            onValueChange = { todoDescription = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            OutlinedTextField(
                value = tododate    ,
                onValueChange = { tododate = it },
                label = { Text("time") },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(16.dp))
            OutlinedTextField(
                value = todotime,
                onValueChange = { todotime = it },
                label = { Text("time") }
            )


        }
        Button(onClick = {
            if (todoTitle.isNotBlank() && todoDescription.isNotBlank()) {
                vModel.updateTodoItem(
                    UiState(
                    id = todoItemId,
                    name = todoTitle,
                    description = todoDescription,
                    date = todoState.date,
                    time = todoState.time,
                    priority = todoState.priority,
                    status = todoState.status
                )
                )
                navigateBack()
            } else {
                showErrorDialog = true
            }
        }) {
            Text("Save")
        }
        Button(onClick = { navigateBack() }) {
            Text(text = "back")
        }
    }

    // Error dialog if fields are empty
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text("Error") },
            text = { Text("Please fill out all fields.") },
            confirmButton = {
                TextButton(
                    onClick = { showErrorDialog = false }
                ) {
                    Text("OK", color = MaterialTheme.colorScheme.primary)
                }
            }
        )
    }
}