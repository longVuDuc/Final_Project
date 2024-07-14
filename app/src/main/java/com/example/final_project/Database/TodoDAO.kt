package com.example.final_project.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {
    @Insert
    suspend fun add(st: TodoItem)
    @Query("SELECT * FROM TodoItem where id = :id")
    fun getTodo(id: Int): Flow<TodoItem?>
    @Query("SELECT * FROM TodoItem")
    fun GetAll(): Flow<List<TodoItem>>
    @Update
    suspend fun update(TodoItem: TodoItem)
    @Delete
    suspend fun delete(TodoItem: TodoItem)
}