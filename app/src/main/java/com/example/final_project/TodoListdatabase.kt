package com.example.final_project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoList::class],version = 1, exportSchema = false)
abstract class TodoListdatabase:RoomDatabase(){
    abstract fun getDao():TodoDAO
    companion object{
        private var db:TodoListdatabase?= null
        fun getDatabase(context: Context):TodoListdatabase{
            return db?: synchronized(this) {
                Room.databaseBuilder(context,TodoListdatabase::class.java,"TodoList")
                    .build()
                    .also { db = it }
            }
        }
    }
}
