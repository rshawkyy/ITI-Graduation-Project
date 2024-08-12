package com.example.finalproject.Repository

import androidx.lifecycle.LiveData
import com.example.finalproject.DatabaseModel.AllDatabase.Dao.UserDao
import com.example.finalproject.DatabaseModel.AllDatabase.Entity.User
import java.util.concurrent.Executors

class UserRepoImp(private val userDao: UserDao) : UserRepo {

    override fun registerUser(user: User) {
        Executors.newSingleThreadExecutor().execute {
            userDao.registerUser(user)
        }
    }

    override fun getUserByUsername(username: String): LiveData<User> {
        return userDao.getUserByUsername(username)
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

    override fun getUserByCredentials(username: String, password: String): LiveData<User> {
        return userDao.getUserByCredentials(username, password)
    }
}