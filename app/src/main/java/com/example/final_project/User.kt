package com.example.final_project
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val firstname: String = "",
    val lastname : String = "",
    val username: String = "",
    val password: String = "",
)

