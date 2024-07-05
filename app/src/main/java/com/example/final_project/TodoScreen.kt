package com.example.final_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TodoScreen( modifier: Modifier = Modifier, vModel: TodoListViewModel = viewModel(factory = AppViewModels.Factory)) {
    val state by vModel.state.collectAsState()
    val list = state.list
    if (list.isEmpty()) {
        EmptyState()
    } else {
       val groupTodo = list.groupBy { it.status }
        LazyColumn {
            groupTodo.forEach { (status, items) ->
                item {
                    TagSection(Status = status, items = items)
                }
            }
        }
    }
}
@Composable
fun TagSection(Status: Status, items: List<TodoList>) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = Status.name,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        items.forEach { item ->
            TodoItemCard(todoItem = item)
        }
    }
}
@Composable
fun TodoItemCard(todoItem: TodoList) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = todoItem.name, style = MaterialTheme.typography.bodyLarge)
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
        Text(
            text = "No To-Do items yet!",
        )
    }
}

