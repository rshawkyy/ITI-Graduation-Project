package com.example.finalproject.Repository

import androidx.lifecycle.LiveData
import com.example.finalproject.DatabaseModel.Models.Meal

interface FavoriteRecipeRepository {

    fun getAllFavoriteRecipes(): LiveData<List<Meal>>
    suspend fun insertFavoriteRecipe(recipe: Meal)
    suspend fun deleteFavoriteRecipe(recipe: Meal)
}