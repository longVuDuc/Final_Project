package com.example.final_project


import java.sql.Time
import java.util.Calendar
import java.util.Date

data class UiState (
    val id: String = "",
    val tags: String = "",
    val name : String = "",
    val description : String = "",
    val date : Date = Date() ,
    val time : Time = Time(System.currentTimeMillis()),
    val priority: Int = 1,
    val status: Status = Status.INPROCESS
)

data class TodoListUiState (
    val list: List<TodoList> = listOf()
)