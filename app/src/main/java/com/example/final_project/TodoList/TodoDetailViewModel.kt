package com.example.final_project.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.Status
import com.example.final_project.Database.TodoDAO
import com.example.final_project.Database.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class TodoDetailViewModel(private val todoDAO: TodoDAO) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state = _state

    fun getTodoItemByID(id: Int) {
        viewModelScope.launch {
            todoDAO.getTodo(id)
                .collect { todoItem ->
                    if (todoItem != null) {
                        _state.value = UiState(
                            id = todoItem.id,
                            name = todoItem.name,
                            description = todoItem.description,
                            date = todoItem.date,
                            time = todoItem.time,
                            priority = todoItem.priority,
                            status = todoItem.status,
                            userID = todoItem.userID
                        )
                    }
                }
        }
    }
    fun delete(id: Int) {
        viewModelScope.launch {
            todoDAO.delete(id)
        }
    }
    fun setComplete() {
        viewModelScope.launch {
            val currentState = _state.value
            val updatedTodo = TodoItem(
                id = currentState.id,
                name = currentState.name,
                description = currentState.description,
                date = currentState.date,
                time = currentState.time,
                priority = currentState.priority,
                status = Status.COMPLETE,
                userID = currentState.userID
            )
            todoDAO.update(updatedTodo)
        }
    }
    fun updateTodoItem() {
        viewModelScope.launch {
            val currentState = _state.value
            val updatedTodo = TodoItem(
                id = currentState.id,
                name = currentState.name,
                description = currentState.description,
                date = currentState.date,
                time = currentState.time,
                priority = currentState.priority,
                status = currentState.status,
                userID = currentState.userID
            )
            todoDAO.update(updatedTodo)
        }
    }
}

