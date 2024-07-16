package com.example.final_project.TodoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.Status
import com.example.final_project.Database.TodoDAO
import com.example.final_project.Database.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class TodoAddViewModel (private val todoDAO: TodoDAO): ViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()
    fun setTag(Tag: String) {
        _state.update {
            it.copy(tags = Tag)
        }
    }
    fun setName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }
    fun setdescription(description: String) {
        _state.update {
            it.copy(description = description)
        }
    }
    fun setdate(date: String) {
        _state.update {
            it.copy(date = date)
        }
    }
    fun settime(time: String) {
        _state.update {
            it.copy(time = time)
        }
    }
    fun setPriority(Priority: Int) {
        _state.update {
            it.copy(priority = Priority)
        }
    }
    fun add() {
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
            todoDAO.add(st)
        }
        _state.update {
            it.copy( tags = "", name = "", description = "", priority = 0, status = Status.INPROCESS)
        }
    }
    fun setcomplete(){
        _state.update {
            it.copy(status = Status.COMPLETE)
        }
    }
}