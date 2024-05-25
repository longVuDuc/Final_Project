package com.example.final_project

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.Date

@Entity
class TodoList(@PrimaryKey val id: String, val tags: String, val name : String ,val description : String, val date : Date, val time : Time, val Priority: Int) {
}