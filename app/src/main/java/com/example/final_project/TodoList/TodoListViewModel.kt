package com.example.final_project.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.TodoDAO
import com.example.final_project.Database.TodoItem
import com.example.final_project.user.UserViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class TodoListViewModel(private val todoDAO: TodoDAO) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<TodoItem>>(emptyList())
    val searchResults: StateFlow<List<TodoItem>> = _searchResults

    fun getTodoItems(userId: Int): Flow<TodoListUiState> {
        return todoDAO.getTodoItemsByUserId(userId).map { TodoListUiState(it) }
    }

    fun performSearch(query: String, userId: Int) {
        viewModelScope.launch {
            val todos = todoDAO.getTodoItemsByUserId(userId).firstOrNull() ?: emptyList()

            val filteredTodos = todos.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
            _searchResults.value = filteredTodos
        }
    }

}

data class TodoListUiState(val todoList: List<TodoItem> = listOf())
