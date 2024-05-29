package com.example.final_project

import android.app.Application

class TodoListApplication: Application (){
    lateinit var dao: TodoDAO
    override fun onCreate() {
        super.onCreate()
        dao = TodoListdatabase.getDatabase(this.applicationContext).getDao()
    }
}