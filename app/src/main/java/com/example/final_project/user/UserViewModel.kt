package com.example.final_project.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.Database.TodoItem
import com.example.final_project.Database.User
import com.example.final_project.Database.UserDAO
import com.example.final_project.TodoList.TodoListUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userDao: UserDAO) : ViewModel() {
    private val _userstate: MutableStateFlow<User> = MutableStateFlow(User())
    val userState: StateFlow<User> = _userstate.asStateFlow()
    private val _userLoggedIn = MutableStateFlow(false)
    fun setFirstname(firstname: String) {
        _userstate.update { it.copy(firstname = firstname) }
    }
    fun setLastname(lastname: String) {
        _userstate.update { it.copy(lastname = lastname) }
    }
    fun setEmail(email: String) {
        _userstate.update { it.copy(email = email) }
    }
    fun setUsername(username: String) {
        _userstate.update { it.copy(username = username) }
    }
    fun setPassword(password: String) {
        _userstate.update { it.copy(password = password) }
    }

    // Add user to the database
    fun add() {
        viewModelScope.launch {
            val user = _userstate.value.copy()
            try {
                withContext(Dispatchers.IO) {
                    userDao.add(user)
                }
                _userstate.update { it.copy(firstname = "", lastname = "", username = "", password = "") }
            } catch (e: Exception) {
                // Handle exception (e.g., log error, show error message)
            }
        }
    }

    fun validateUserCredentials(username: String, password: String, onSuccess: (Int) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val user = withContext(Dispatchers.IO) {
                    userDao.getUser(username, password)
                }
                if (user != null) {
                    _userLoggedIn.value = true
                    onSuccess(user.id)
                } else {
                    onError("Invalid credentials")
                }
            } catch (e: Exception) {
                onError("Error validating credentials")
            }
        }
    }


    // Update user profile
    fun updateProfile() {
        viewModelScope.launch {
            val st = User(
                id = _userstate.value.id,
                firstname = _userstate.value.firstname,
                lastname = _userstate.value.lastname,
                email = _userstate.value.email,
            )
            userDao.update(st)
        }
    }

    fun changePassword(newPassword: String) {
        viewModelScope.launch {
            val st = User(
                password = newPassword
            )
            userDao.update(st)
        }
    }
    fun fetchUserById(userId: Int): StateFlow<User?> {
        val userFlow = MutableStateFlow<User?>(null)
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDao.getUserbyID(userId)
            }
            userFlow.value = user
        }
        return userFlow.asStateFlow()
    }
    // Logout user
    fun logoutUser() {
        _userLoggedIn.value = false
    }
}
