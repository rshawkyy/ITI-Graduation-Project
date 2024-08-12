package com.example.finalproject.Repository

import RandomMealResponse
import retrofit2.Response

interface RandomImageRepository {

    suspend fun getRandomMeal(): Response<RandomMealResponse>

}