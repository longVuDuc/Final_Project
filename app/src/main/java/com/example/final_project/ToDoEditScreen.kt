package com.example.final_project

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import java.util.Date
import java.util.Locale

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(
    vModel: TodoDetailViewModel = viewModel(factory = AppViewModels.Factory)
) {
    var dateResult by remember { mutableStateOf("Date")}
    val openDialog = remember {
        mutableStateOf(false)
    }
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
        Button(onClick = { openDialog.value = true }) {
            Text(dateResult)
        }
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
        if(openDialog.value){
            val datePickerState = rememberDatePickerState()
            val confirmEndabled =
                derivedStateOf { datePickerState.selectedDateMillis != null }

            DatePickerDialog(
                onDismissRequest = { openDialog.value = false },
                confirmButton = {
                    TextButton(
                        onClick = { openDialog.value = false
                            var date  = "No selection"
                            if(datePickerState.selectedDateMillis != null){
                                date = convertLongToDateString(datePickerState.selectedDateMillis!!)
                            }
                            dateResult = date
                            vModel.setdate(dateResult)
                        },
                        enabled = confirmEndabled.value
                        ) {
                        Text(text = "Select")
                    }
                }) {
                    DatePicker(state = datePickerState)
            }
        }
    }
}
fun convertLongToDateString(timeInMillis: Long): String {
    val date = Date(timeInMillis)
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(date)
}