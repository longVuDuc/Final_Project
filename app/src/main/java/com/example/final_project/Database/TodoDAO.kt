package com.example.final_project.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.final_project.TodoList.UiState
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {
    @Insert
    suspend fun add(todoItem: TodoItem)

    @Query("SELECT * FROM TodoItem WHERE id = :id")
    fun getTodo(id: Int): Flow<TodoItem?>

    @Query("SELECT * FROM TodoItem WHERE userId  = :userId")
    fun getTodoItemsByUserId(userId: Int): Flow<List<TodoItem>>

    @Update
    suspend fun update(todoItem: TodoItem)

    @Query("DELETE FROM TodoItem WHERE id = :id")
    suspend fun delete(id: Int)
}