package com.example.final_project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.Date

class TodoDetailViewModel (private val dao: TodoDAO): ViewModel() {
    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun setId(id: String) {
        _state.update {
            it.copy( id = id)
        }
    }
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
    fun setdate(date: Date) {
        _state.update {
            it.copy(date = date)
        }
    }
    fun settime(time: Time) {
        _state.update {
            it.copy(time = time)
        }
    }
    fun setPriority(Priority: Int) {
        _state.update {
            it.copy(Priority = Priority)
        }
    }
    fun add() {
        viewModelScope.launch {
            val st = TodoList(
                id = _state.value.id,
                tags = _state.value.tags,
                name = _state.value.name,
                description = _state.value.description,
                date = _state.value.date,
                time = _state.value.time,
                Priority = _state.value.Priority
                )
            dao.add(st)
        }
        _state.update {
            it.copy(id = "", tags = "", name = "", description = "", Priority = 0)
        }
    }
}