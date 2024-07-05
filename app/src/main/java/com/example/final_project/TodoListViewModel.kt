package com.example.final_project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class TodoListViewModel(private val dao: TodoDAO): ViewModel() {
    val state: StateFlow<TodoListUiState>
        get() {
            return dao.GetAll().map { TodoListUiState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = TodoListUiState()
                )
        }
}