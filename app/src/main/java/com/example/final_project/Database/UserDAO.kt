package com.example.final_project.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert
    suspend fun add(user: User)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): User?
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserbyID(id: Int): User
    @Update
    suspend fun update(user: User)
}
