package com.example.finalproject.Retrofit

import CategoryResponse
import MealDetail
import MealsResponse
import RandomMealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFood {

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("i") category: String): Response<MealsResponse>

    @GET("random.php")
    suspend fun getRandomMeal(): Response<RandomMealResponse>

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): Response<RandomMealResponse>



    @GET("search.php")
    suspend fun getMealByName(@Query("s") name: String): Response<MealDetail>
    @GET("search.php")
    suspend fun getMealByNameForSearch(@Query("s") name: String): Response<RandomMealResponse>

}