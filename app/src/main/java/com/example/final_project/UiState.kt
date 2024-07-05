package com.example.final_project


import android.text.LoginFilter.UsernameFilterGMail
import java.sql.Time
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class UiState (
    val id: Int = 0,
    val tags: String = "",
    val name : String = "",
    val description : String = "",
    val date : String = LocalDate.now().toString(),
    val time : String = LocalDateTime.now().toString(),
    val priority: Int = 1,
    val status: Status = Status.INPROCESS
)
data class user (
    val id: Int = 0,
    val firstname: String = "",
    val lastname : String = "",
    val username : String = "",
    val password : String = ""
)

data class TodoListUiState (
    val list: List<TodoList> = listOf()
)