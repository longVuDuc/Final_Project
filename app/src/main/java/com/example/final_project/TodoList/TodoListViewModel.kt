package com.example.final_project.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.TodoDAO
import com.example.final_project.Database.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TodoListViewModel(private val todoDAO: TodoDAO) : ViewModel() {
    val TodolistUiState: StateFlow<TodoListUiState>
        get() {
            return todoDAO.GetAll().map { TodoListUiState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = TodoListUiState()
                )
        }

    private val _searchResults = MutableStateFlow<List<TodoItem>>(emptyList())
    val searchResults: StateFlow<List<TodoItem>> = _searchResults

    fun performSearch(query: String) {
        viewModelScope.launch {
            val allTodos = todoDAO.GetAll().firstOrNull() ?: emptyList()
            _searchResults.value = allTodos.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
        }
    }
}

data class TodoListUiState(val todoList: List<TodoItem> = listOf())
