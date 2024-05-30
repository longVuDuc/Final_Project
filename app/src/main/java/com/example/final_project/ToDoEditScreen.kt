package com.example.final_project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import java.util.Date

@Composable
fun TodoEditScreen(
    vModel: TodoDetailViewModel = viewModel(factory = AppViewModels.Factory)
) {
    val state by vModel.state.collectAsState()
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(value = state.name,
            onValueChange = { vModel.setName(it) },
            label = { Text(text = "Name")
            })
        TextField(value = state.description,
            onValueChange = { vModel.setdescription(it) },
            label = { Text(text = "Description")
            })
//        Button(onClick = { showDatePickerDialog { date -> dueDate = date } }) {
//            Text("Select Due Date")
//        }
//        DropdownMenu(
//            expanded = true,
//            onDismissRequest = { /*TODO*/ }
//        ) {
//            DropdownMenuItem(onClick = { }) {
//            }
//            DropdownMenuItem(onClick = {  }) {
//            }
//            DropdownMenuItem(onClick = {  }) {
//            }
//        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {  }) {
                Text("Cancel")
            }
            Button(onClick = { vModel.add()}) {
                Text("Add")
            }
        }
    }
}

fun showDatePickerDialog(onDateSelected: (Date) -> Unit) {
    // Implement a date picker dialog here
}
