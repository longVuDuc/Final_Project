package com.example.final_project.Database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "TodoItem")
class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name : String ,
    val description : String,
    val date : String,
    val time : String,
    val priority: Int,
    val status: Status = Status.INPROCESS,
    val userID : Int
) {
}

enum class Status {
    COMPLETE,
    INPROCESS
}