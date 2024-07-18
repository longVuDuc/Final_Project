package com.example.final_project.TodoList

import com.example.final_project.Database.Status
import java.time.LocalDate
import java.time.LocalDateTime


data class UiState (
    val id: Int = 0,
    val name : String = "",
    val description : String = "",
    val date : String = LocalDate.now().toString(),
    val time : String = LocalDateTime.now().toString(),
    val priority: Int = 1,
    val status: Status = Status.INPROCESS,
    val userID: Int = 0,
)
