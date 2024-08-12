package com.example.finalproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.Repository.MealRepo

class ViewModelFactory (var mealRepository: MealRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MVVM(mealRepository) as T
    }
}