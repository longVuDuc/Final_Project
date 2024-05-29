package com.example.final_project

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity
class TodoList(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val tags: String,
    val name : String ,
    val description : String,
    val date : Date,
    val time : Time,
    val priority: Int,
    val status: Status = Status.INPROCESS
) {
}

enum class Status {
    COMPLETE,
    INPROCESS,
    POSTPONED
}