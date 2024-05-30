package com.example.final_project

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class TodoList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val tags: String,
    val name : String ,
    val description : String,
    val date : String,
    val time : String,
    val priority: Int,
    val status: Status = Status.INPROCESS
) {
}

enum class Status {
    COMPLETE,
    INPROCESS,
    POSTPONED
}