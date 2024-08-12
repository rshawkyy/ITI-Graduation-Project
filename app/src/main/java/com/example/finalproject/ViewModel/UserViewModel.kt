package com.example.finalproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.DatabaseModel.AllDatabase.Entity.User
import com.example.finalproject.Repository.UserRepo

class UserViewModel (private val userRepository: UserRepo) : ViewModel() {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> = _loginStatus

    private val _userExistsStatus = MutableLiveData<Boolean>()
    val userExistsStatus: LiveData<Boolean> = _userExistsStatus

    fun registerUser(user:User) {
        userRepository.registerUser(user)
    }

    fun getUserByUsername(username: String) {
        userRepository.getUserByUsername(username).observeForever { user ->
            _userLiveData.value = user
            _userLiveData.value = user
            _userExistsStatus.value = user != null
        }
    }
    fun loginUser(username: String, password: String) {
        userRepository.getUserByCredentials(username, password).observeForever { user ->
            if (user != null) {
                _userLiveData.value = user
                _loginStatus.value = true
            } else {
                _loginStatus.value = false
            }
        }
    }
    fun checkUserExists(username: String) {
        userRepository.getUserByUsername(username).observeForever { user ->
            _userExistsStatus.value = user != null
        }
    }
}