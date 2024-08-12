package com.example.finalproject.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.Repository.FavoriteRecipeRepository

class FavouriteRecipeVMFactory(private val repository: FavoriteRecipeRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteRecipeVM(repository) as T
    }
}