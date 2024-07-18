package com.example.final_project.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.final_project.Database.TodoItem

@Composable
fun TodoCard(todo: TodoItem,
             onItemClick: (Int) -> Unit,
             ) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
    ) {
        Box(modifier = Modifier.clickable{ onItemClick(todo.id)}
        ){
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = todo.name, fontSize = 18.sp, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.CheckCircle,
                        tint = if (todo.status.toString() == "INPROCESS") Color.White else Color.Green,
                        contentDescription = "check Icon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = todo.date, fontSize = 14.sp, style = MaterialTheme.typography.bodySmall)
                    Text(text = " At ", fontSize = 14.sp, style = MaterialTheme.typography.bodySmall)
                    Text(text = todo.time, fontSize = 14.sp, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Blue,
                                shape = RoundedCornerShape(2.dp)
                            )
                            .padding(4.dp)
                    ){
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Icon(Icons.Outlined.Flag, tint = Color.White, contentDescription = "flag Icon")
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = todo.priority.toString(), fontSize = 14.sp, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}