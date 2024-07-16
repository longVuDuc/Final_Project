package com.example.final_project.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.TodoItem
import com.example.final_project.Database.User
import com.example.final_project.Database.UserDAO
import com.example.final_project.TodoList.UiState
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
    private val _userLoggedIn = MutableStateFlow(false)

    var loggedInUserId: Int? = null
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
    fun setemail(email: String) {
        _state.update {
            it.copy(email = email)
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
                email = _state.value.email,
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
                loggedInUserId = user.id
                _userLoggedIn.value = true
                onSuccess()
            } else {
                onError("Invalid credentials")
            }
        }
    }
    fun updateProfile() {
        viewModelScope.launch {
            val user = User(
                firstname = state.value.firstname,
                lastname = state.value.lastname,
                email = state.value.email,
            )
            userDao.update(user)
        }
    }
    fun changepassword(){
        viewModelScope.launch {
            val user = User(
                password = state.value.password
            )
            userDao.update(user)
        }
    }
    fun logoutUser() {
        loggedInUserId = null
        _userLoggedIn.value = false
    }
}
