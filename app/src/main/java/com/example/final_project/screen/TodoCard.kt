package com.example.final_project.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.final_project.Database.TodoItem

@Composable
fun TodoCard(todo: TodoItem, onItemClick: (Int) -> Unit) {
    Card(modifier = Modifier
    .padding(8.dp)
    .fillMaxWidth()
    ) {
        Box(Modifier.clickable{ onItemClick(todo.id)}){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = todo.name, fontSize = 24.sp, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = todo.description, fontSize = 20.sp, style = MaterialTheme.typography.bodyMedium)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = todo.date, fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = todo.time, fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = todo.tags, fontSize = 16.sp, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}