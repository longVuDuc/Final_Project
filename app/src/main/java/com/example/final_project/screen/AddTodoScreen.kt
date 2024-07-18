package com.example.final_project.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.TimePicker
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoAddViewModel
import com.example.final_project.navigation.NavigationDestination
import java.util.*


object AddTodoDestination : NavigationDestination {
    override val route = "Add_ToDo"
    override val titleRes = R.string.add_todo_title
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    navigateTohome: () -> Unit,
    userID : Int,
    vModel: TodoAddViewModel = viewModel(factory = AppViewModels.Factory)
) {
    var dateResult by remember { mutableStateOf("Today") }
    val openDateDialog = remember { mutableStateOf(false) }
    var timeResult by remember { mutableStateOf("Time") }
    val openTimeDialog = remember { mutableStateOf(false) }
    val state by vModel.state.collectAsState()
    val context = LocalContext.current
    var dropdownExpanded by remember { mutableStateOf(false) }
    var priority by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navigateTohome() }) {
            Icon(Icons.Filled.Close, tint = Color.White, contentDescription = "Close Icon")
        }
        Text(text = "Create new task", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = state.name,
            onValueChange = { vModel.setName(it) },
            label = { Text(text = "Name", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.description,
            onValueChange = { vModel.setdescription(it) },
            label = { Text(text = "Description") },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Filled.Flag, tint = Color.White, contentDescription = "flag Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Priority", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = it },
                modifier = Modifier.width(80.dp)
            ) {
                TextField(
                    readOnly = true,
                    value = priority,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropdownExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..4) {
                        DropdownMenuItem(
                            text = { Text(text = i.toString()) },
                            onClick = {
                                dropdownExpanded = false
                                vModel.setPriority(i)
                                priority = i.toString()
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Filled.DateRange, tint = Color.White, contentDescription = "date Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Date", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { openDateDialog.value = true },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636))
            ) {
                Text(dateResult, color = Color.White, fontSize = 16.sp)
            }
            if (openDateDialog.value) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = { openDateDialog.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                openDateDialog.value = false
                                dateResult = datePickerState.selectedDateMillis?.let {
                                    convertLongToDateString(it)
                                } ?: "No selection"
                                vModel.setdate(dateResult)
                            },
                            enabled = confirmEnabled.value
                        ) {
                            Text(text = "Select")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { openDateDialog.value = false }) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Icon(Icons.Filled.AccessTime, tint = Color.White, contentDescription = "time Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Time", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { openTimeDialog.value = true },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636))
            ) {
                Text(timeResult, color = Color.White, fontSize = 16.sp)
            }
            if (openTimeDialog.value) {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                TimePickerDialog(
                    context,
                    { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                        timeResult = String.format("%02d:%02d", selectedHour, selectedMinute)
                        openTimeDialog.value = false
                        vModel.settime(timeResult)
                    },
                    hour,
                    minute,
                    true
                ).show()
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                vModel.setUserid(userID)
                vModel.add()
                navigateTohome()
                      },
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Add")
        }
    }
}

fun convertLongToDateString(timeInMillis: Long): String {
    val date = Date(timeInMillis)
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(date)
}
