package com.example.final_project

import android.app.Application

class TodoListApplication: Application (){
    lateinit var tododao: TodoDAO
    lateinit var userdao: UserDAO
    override fun onCreate() {
        super.onCreate()
        tododao = AppDatabase.getDatabase(this.applicationContext).todoDao()
        userdao = AppDatabase.getDatabase(this.applicationContext).userDao()
    }
}