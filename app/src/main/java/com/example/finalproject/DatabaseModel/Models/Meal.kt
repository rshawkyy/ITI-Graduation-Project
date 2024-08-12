package com.example.finalproject.DatabaseModel.Models
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favorite_recipes")
data class Meal(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)