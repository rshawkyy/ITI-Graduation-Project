package com.example.finalproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.DatabaseModel.Models.Meal
import com.example.finalproject.Repository.FavoriteRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavouriteRecipeVM(private val repository: FavoriteRecipeRepository) : ViewModel() {

    fun insert(favoriteRecipe: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavoriteRecipe(favoriteRecipe)
        }
    }

    fun delete(favoriteRecipe: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteRecipe(favoriteRecipe)
        }
    }

    fun getAllFavoriteRecipes(): LiveData<List<Meal>> {
        return repository.getAllFavoriteRecipes()
    }
}