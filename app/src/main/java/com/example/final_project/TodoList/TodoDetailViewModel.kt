package com.example.final_project.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.TodoDAO
import com.example.final_project.Database.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch


class TodoDetailViewModel(private val todoDAO: TodoDAO) : ViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    fun getTodoItemByID(id: Int) {
        viewModelScope.launch {
            todoDAO.getTodo(id)
                .filterNotNull()
                .collect { todoItem ->
                    _state.value = UiState(
                        id = todoItem.id,
                        tags = todoItem.tags,
                        name = todoItem.name,
                        description = todoItem.description,
                        date = todoItem.date,
                        time = todoItem.time,
                        priority = todoItem.priority,
                        status = todoItem.status
                    )
                }
        }
    }
    fun updateTodoItem(uiState: UiState) {
        viewModelScope.launch {
            val st = TodoItem(
                id = _state.value.id,
                tags = _state.value.tags,
                name = _state.value.name,
                description = _state.value.description,
                date = _state.value.date,
                time = _state.value.time,
                priority = _state.value.priority,
            )
            todoDAO.update(st)
        }
    }
}
