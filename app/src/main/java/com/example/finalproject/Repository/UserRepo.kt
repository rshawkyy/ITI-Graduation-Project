package com.example.finalproject.Repository

import androidx.lifecycle.LiveData
import com.example.finalproject.DatabaseModel.AllDatabase.Entity.User

interface UserRepo {

    fun registerUser(user: User)
    fun getUserByUsername(username: String): LiveData<User>
    fun getAllUsers(): LiveData<List<User>>
    fun getUserByCredentials(username: String, password: String): LiveData<User>
}