package com.example.final_project.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.TimePicker
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoAddViewModel
import com.example.final_project.navigation.NavigationDestination
import kotlin.math.round

object AddTodoDestination : NavigationDestination {
    override val route = "Add_ToDo"
    override val titleRes = R.string.add_todo_title
}
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    navigateTohome : () -> Unit,
    vModel: TodoAddViewModel = viewModel(factory = AppViewModels.Factory)
) {
    var dateResult by remember { mutableStateOf("Today") }
    val opendateDialog = remember { mutableStateOf(false) }
    var timeResult by remember { mutableStateOf("Time") }
    val opentimeDialog = remember { mutableStateOf(false) }
    val state by vModel.state.collectAsState()
    val context = LocalContext.current
    var dropdownExpanded by remember { mutableStateOf(false) }
    var priority by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navigateTohome()}) {
            Icon(Icons.Filled.Close, tint = Color.White, contentDescription = "Close Icon")
        }
        Text(text = "Create new task")
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
            Text(text = "Priority", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(200.dp))
            ExposedDropdownMenuBox(expanded = dropdownExpanded,
                onExpandedChange = {dropdownExpanded = it},
                modifier = Modifier.width(80.dp)
            ) {
                TextField(
                    readOnly = true,
                    value = priority,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = dropdownExpanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                {
                    DropdownMenuItem(
                        text = { Text(text = "1") },
                        onClick = {
                            dropdownExpanded = false
                            vModel.setPriority(1)
                            priority = "1"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "2") },
                        onClick = {
                            dropdownExpanded = false
                            vModel.setPriority(2)
                            priority = "2"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "3") },
                        onClick = {
                            dropdownExpanded = false
                            vModel.setPriority(3)
                            priority = "3"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "4") },
                        onClick = {
                            dropdownExpanded = false
                            vModel.setPriority(4)
                            priority = "4"
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)){
            Text(text = "Date", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(215.dp))
            Button(onClick = { opendateDialog.value = true },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636))
            ){
                Text(dateResult, color = Color.White, fontSize = 16.sp)
            }
            if (opendateDialog.value) {
                val datePickerState = rememberDatePickerState()
                val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
                DatePickerDialog(
                    onDismissRequest = { opendateDialog.value = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                opendateDialog.value = false
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
                        TextButton(
                            onClick = {
                                opendateDialog.value = false
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            Text(text = "Time", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(215.dp))
            Button(onClick = { opentimeDialog.value = true },
                shape = RoundedCornerShape(7.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF363636))
                ) {
                Text(timeResult, color = Color.White, fontSize = 16.sp)
            }
            if (opentimeDialog.value) {
                val calendar = Calendar.getInstance()
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                TimePickerDialog(
                    context,
                    { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                        timeResult = String.format("%02d:%02d", selectedHour, selectedMinute)
                        opentimeDialog.value = false
                        vModel.settime(timeResult)
                    },
                    hour,
                    minute,
                    true,
                ).show()
            }
        }
        Spacer(modifier = Modifier.height(200.dp))
        Button(onClick = { vModel.add() },
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier.fillMaxWidth()
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
