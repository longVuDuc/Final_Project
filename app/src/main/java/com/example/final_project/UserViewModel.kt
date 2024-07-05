package com.example.final_project

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao: UserDAO) : ViewModel() {
    private val _state: MutableStateFlow<User> = MutableStateFlow(User())
    val state: StateFlow<User> = _state.asStateFlow()
    fun setfirstname(firstname: String) {
        _state.update {
            it.copy(firstname = firstname)
        }
    }
    fun setlastname(lastname: String) {
        _state.update {
            it.copy(lastname = lastname)
        }
    }
    fun setusername(username: String) {
        _state.update {
            it.copy(username = username)
        }
    }
    fun setpassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    fun add() {
        viewModelScope.launch {
            val user = User(
                id = _state.value.id,
                firstname = _state.value.firstname,
                lastname = _state.value.lastname,
                username = _state.value.username,
                password = _state.value.password,
            )
            userDao.add(user)
        }
        _state.update {
            it.copy(firstname = "", lastname = "", username = "", password = "")
        }
    }

    fun validateUserCredentials(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getUser(username, password)
            }
            if (user != null) {
                onSuccess()
            } else {
                onError("Invalid credentials")
            }
        }
    }
}
