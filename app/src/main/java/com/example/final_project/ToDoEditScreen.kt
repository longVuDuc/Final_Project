package com.example.final_project

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.TimePicker
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.ui.platform.LocalContext

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoEditScreen(
    vModel: TodoDetailViewModel = viewModel(factory = AppViewModels.Factory)
) {
    var dateResult by remember { mutableStateOf("Date") }
    val opendateDialog = remember { mutableStateOf(false) }
    var timeResult by remember { mutableStateOf("Time") }
    val opentimeDialog = remember { mutableStateOf(false) }
    val state by vModel.state.collectAsState()
    val context = LocalContext.current
    var dropdownExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            value = state.name,
            onValueChange = { vModel.setName(it) },
            label = { Text(text = "Name") }
        )
        TextField(
            value = state.description,
            onValueChange = { vModel.setdescription(it) },
            label = { Text(text = "Description") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { opendateDialog.value = true }) {
            Text(dateResult)
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
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { opentimeDialog.value = true }) {
            Text(timeResult)
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* Handle cancel action */ }) {
                Text("Cancel")
            }
            Button(onClick = { vModel.add() }) {
                Text("Add")
            }
        }

    }
}

fun convertLongToDateString(timeInMillis: Long): String {
    val date = Date(timeInMillis)
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(date)
}