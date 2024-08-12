package com.example.finalproject.DatabaseModel.AllDatabase.Dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalproject.DatabaseModel.Models.Meal

@Dao
interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteRecipe(recipe: Meal)

    @Delete
    fun deleteFavoriteRecipe(recipe: Meal)

    @Query("SELECT * FROM favorite_recipes")
    fun getAllFavoriteRecipes(): LiveData<List<Meal>>
}