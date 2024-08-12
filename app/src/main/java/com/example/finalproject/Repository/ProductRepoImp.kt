package com.example.finalproject.Repository

import CategoryResponse
import MealDetail
import MealsResponse
import RandomMealResponse
import com.example.finalproject.Retrofit.RemoteDataSource
import retrofit2.Response

class ProductRepoImp (var remoteDataSource: RemoteDataSource): MealRepo {
    override suspend fun getCategoryResponse(): Response<CategoryResponse> {
        return remoteDataSource.getCategoryResponse()
    }

    override suspend fun getMealsByCategory(category: String): Response<MealsResponse> {
        return  remoteDataSource.getMealsByCategory(category)
    }

    override suspend fun getMealByName(s: String): Response<MealDetail> {
        return remoteDataSource.getMealByName(s)
    }

    override suspend fun getRandomMeal(): Response<RandomMealResponse> {
        return remoteDataSource.getRandomMeal()
    }

    override suspend fun getMealById(id: String): Response<RandomMealResponse> {
        return remoteDataSource.getMealById(id)
    }

    override suspend fun getMealByNameForSearch(name: String): Response<RandomMealResponse> {
        return remoteDataSource.getMealByNameForSearch(name)
    }
}