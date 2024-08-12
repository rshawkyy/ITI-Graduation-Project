package com.example.finalproject.Repository

import RandomMealResponse
import com.example.finalproject.Retrofit.ApiClient
import retrofit2.Response

class RandomImageRepoImp (private val apiClient: ApiClient) : RandomImageRepository {
    override suspend fun getRandomMeal(): Response<RandomMealResponse> {
        return apiClient.getRandomMeal()    }
}