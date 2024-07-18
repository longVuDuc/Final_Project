package com.example.final_project.screen

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.TimePicker
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Flag
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.final_project.AppViewModels
import com.example.final_project.R
import com.example.final_project.TodoList.TodoDetailViewModel
import com.example.final_project.TodoList.UiState
import com.example.final_project.navigation.NavigationDestination
import java.util.Calendar


object TodoEditDestination : NavigationDestination {
    override val route = "todo_edit"
    override val titleRes = R.string.edit_todo_title
    const val todoIdArg = "todoId"
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(
    todoItemId: Int,
    navigateBack: () -> Unit,
    vModel: TodoDetailViewModel = viewModel(factory = AppViewModels.Factory)
) {
    LaunchedEffect(todoItemId) {
        vModel.getTodoItemByID(todoItemId)
    }
    val todoState by vModel.state.collectAsState()
    val context = LocalContext.current
    var todoTitle by remember(todoState) { mutableStateOf(todoState.name) }
    var todoDescription by remember(todoState) { mutableStateOf(todoState.description) }
    var todotime by remember(todoState) { mutableStateOf(todoState.time) }
    var tododate by remember(todoState) { mutableStateOf(todoState.date) }
    var priority by remember(todoState) { mutableStateOf(todoState.priority.toString()) }
    var dropdownExpanded by remember { mutableStateOf(false) }
    var dateResult by remember { mutableStateOf(todoState.date) }
    val opendateDialog = remember { mutableStateOf(false) }
    var timeResult by remember { mutableStateOf(todoState.time) }
    val opentimeDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { navigateBack() }) {
            Icon(Icons.Filled.Close, tint = Color.White, contentDescription = "Close Icon")
        }
        Text(text = "Edit Task", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            value = todoTitle,
            onValueChange = { todoTitle = it },
            label = { Text(text = "Name", fontSize = 20.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = todoDescription,
            onValueChange = { todoDescription = it },
            label = { Text(text = "Description") },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
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
            Spacer(modifier = Modifier.width(200.dp))
            ExposedDropdownMenuBox(expanded = dropdownExpanded,
                onExpandedChange = { dropdownExpanded = it },
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
                            priority = "1"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "2") },
                        onClick = {
                            dropdownExpanded = false
                            priority = "2"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "3") },
                        onClick = {
                            dropdownExpanded = false
                            priority = "3"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "4") },
                        onClick = {
                            dropdownExpanded = false
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
            Icon(Icons.Filled.DateRange, tint = Color.White, contentDescription = "date Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Date", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
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
                                tododate = dateResult
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
            Icon(Icons.Filled.AccessTime, tint = Color.White, contentDescription = "time Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Time", fontSize = 20.sp)
            Spacer(modifier = Modifier.weight(1f))
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
                        todotime = timeResult
                    },
                    hour,
                    minute,
                    true
                ).show()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(onClick = {
            vModel.setComplete()
            navigateBack()
        }) {
            Text(text = "Set Complete")
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton (onClick = {
            vModel.delete(todoItemId)
            navigateBack()
        }){
            Icon(Icons.Filled.Delete, tint = Color.Red, contentDescription = "delete Icon")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Delete", color = Color.Red, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
                vModel.updateTodoItem()
                navigateBack()
        },
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .height(60.dp)
                .width(320.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Save", color = Color.White, fontSize = 25.sp)
        }

    }
}
