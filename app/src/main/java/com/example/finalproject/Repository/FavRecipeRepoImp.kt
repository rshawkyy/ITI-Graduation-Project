package com.example.finalproject.Repository

import androidx.lifecycle.LiveData
import com.example.finalproject.DatabaseModel.AllDatabase.Dao.FavoriteRecipeDao
import com.example.finalproject.DatabaseModel.Models.Meal

class FavRecipeRepoImp (private val favoriteRecipeDao: FavoriteRecipeDao) : FavoriteRecipeRepository {

    override suspend fun insertFavoriteRecipe(recipe: Meal) {
        favoriteRecipeDao.insertFavoriteRecipe(recipe)
    }

    override suspend fun deleteFavoriteRecipe(recipe: Meal) {
        favoriteRecipeDao.deleteFavoriteRecipe(recipe)
    }

    override fun getAllFavoriteRecipes(): LiveData<List<Meal>> {
        return favoriteRecipeDao.getAllFavoriteRecipes()
    }
}