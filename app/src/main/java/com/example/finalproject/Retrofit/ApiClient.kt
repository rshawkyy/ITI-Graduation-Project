package com.example.finalproject.Retrofit

import CategoryResponse
import MealDetail
import MealsResponse
import RandomMealResponse
import retrofit2.Response

object ApiClient : RemoteDataSource {

    override suspend fun getCategoryResponse(): Response<CategoryResponse> {
        return RetrofitClient.retrofit.create(ApiFood::class.java).getCategories()
    }

    override suspend fun getMealsByCategory(category: String): Response<MealsResponse> {
        return RetrofitClient.retrofit.create(ApiFood::class.java).getMealsByCategory(category)
    }

    override suspend fun getMealByName(s: String): Response<MealDetail> {
        return RetrofitClient.retrofit.create(ApiFood::class.java).getMealByName(s)
    }

    override suspend fun getRandomMeal(): Response<RandomMealResponse> {
        return RetrofitClient.retrofit.create(ApiFood::class.java).getRandomMeal()
    }

    override suspend fun getMealById(id: String): Response<RandomMealResponse> {
        return RetrofitClient.retrofit.create(ApiFood::class.java).getMealById(id)
    }

    override suspend fun getMealByNameForSearch(name: String): Response<RandomMealResponse> {
        return RetrofitClient.retrofit.create(ApiFood::class.java).getMealByNameForSearch(name)
    }

}