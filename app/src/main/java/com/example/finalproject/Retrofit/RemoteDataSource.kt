package com.example.finalproject.Retrofit

import CategoryResponse
import MealDetail
import MealsResponse
import RandomMealResponse
import retrofit2.Response
import retrofit2.http.Query

interface RemoteDataSource {

    suspend fun getCategoryResponse(): Response<CategoryResponse>
    suspend   fun getMealsByCategory(@Query("i") category:String): Response<MealsResponse>
    suspend fun   getMealByName(@Query("s") s:String): Response<MealDetail>
    suspend  fun getRandomMeal(): Response<RandomMealResponse>
    suspend fun getMealById(@Query("i") id:String): Response<RandomMealResponse>
    suspend fun getMealByNameForSearch(@Query("s") name: String): Response<RandomMealResponse>

}