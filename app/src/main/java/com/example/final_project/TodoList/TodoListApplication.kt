package com.example.final_project.TodoList

import android.app.Application
import com.example.final_project.Database.AppDatabase
import com.example.final_project.Database.TodoDAO
import com.example.final_project.Database.UserDAO

class TodoListApplication: Application (){
    lateinit var tododao: TodoDAO
    lateinit var userdao: UserDAO
    override fun onCreate() {
        super.onCreate()
        tododao = AppDatabase.getDatabase(this.applicationContext).todoDao()
        userdao = AppDatabase.getDatabase(this.applicationContext).userDao()
    }
}