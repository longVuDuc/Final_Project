package com.example.final_project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {
    @Insert
    suspend fun add(st: TodoList)

    @Query("SELECT * FROM TodoList")
    fun GetAll(): Flow<List<TodoList>>
}