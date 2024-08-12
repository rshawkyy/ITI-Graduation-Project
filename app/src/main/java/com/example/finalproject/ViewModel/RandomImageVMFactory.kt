package com.example.finalproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.Repository.RandomImageRepository

class RandomImageVMFactory (private val randomImageRepository: RandomImageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomImageVM(randomImageRepository) as T

    }
}