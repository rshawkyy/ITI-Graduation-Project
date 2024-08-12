package com.example.finalproject.ViewModel

import CategoryResponse
import MealDetail
import MealsResponse
import RandomMealResponse
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Repository.MealRepo
import kotlinx.coroutines.launch

class MVVM (private val mealRepository: MealRepo) : ViewModel() {
    private val _categoryResponse = MutableLiveData<CategoryResponse>()
    val categoryResponse: LiveData<CategoryResponse> = _categoryResponse

    private val _mealsByCategory = MutableLiveData<MealsResponse>()
    val mealsByCategory: LiveData<MealsResponse> = _mealsByCategory

    private val _mealByName = MutableLiveData<MealDetail>()
    val mealByName: LiveData<MealDetail> = _mealByName

    private val _randomMeal = MutableLiveData<RandomMealResponse>()
    val randomMeal: LiveData<RandomMealResponse> = _randomMeal

    private val _mealByNameForSearch = MutableLiveData<RandomMealResponse>()
    val mealByNameForSearch: LiveData<RandomMealResponse> = _mealByNameForSearch

    private val _mealById = MutableLiveData<RandomMealResponse>()
    val mealById: LiveData<RandomMealResponse> = _mealById

    fun getAllCategories() {
        viewModelScope.launch {
            try {
                val result = mealRepository.getCategoryResponse()
                if (result.isSuccessful) {
                    result.body()?.let {
                        Log.d("MVVM", "API response body: $it")
                        _categoryResponse.value = it
                    } ?: run {
                        Log.e("MVVM", "API response body is null")
                    }
                } else {
                    Log.e("MVVM", "API request failed with status: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.e("MVVM", "Error fetching categories", e)
            }
        }
    }

    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            try {
                val result = mealRepository.getMealsByCategory(category)
                if (result.isSuccessful) {
                    _mealsByCategory.value = result.body()
                } else {
                    Log.e("MVVM", "API request failed with status: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.e("MVVM", "Error fetching meals by category", e)
            }
        }
    }

    fun getMealByName(name: String) {
        viewModelScope.launch {
            try {
                val result = mealRepository.getMealByName(name)
                if (result.isSuccessful) {
                    _mealByName.value = result.body()
                } else {
                    Log.e("MVVM", "API request failed with status: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.e("MVVM", "Error fetching meal by name", e)
            }
        }
    }

    fun getMealByNameForSearch(name: String) {
        viewModelScope.launch {
            try {
                val result = mealRepository.getMealByNameForSearch(name)
                if (result.isSuccessful) {
                    _mealByNameForSearch.value = result.body()
                } else {
                    Log.e("MVVM", "API request failed with status: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.e("MVVM", "Error fetching meal by name for search", e)
            }
        }
    }

    fun getRandomMeal() {
        viewModelScope.launch {
            try {
                val result = mealRepository.getRandomMeal()
                if (result.isSuccessful) {
                    _randomMeal.value = result.body()
                } else {
                    Log.e("MVVM", "API request failed with status: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.e("MVVM", "Error fetching random meal", e)
            }
        }
    }

    fun getMealById(id: String) {
        viewModelScope.launch {
            try {
                val result = mealRepository.getMealById(id)
                if (result.isSuccessful) {
                    _mealById.value = result.body()
                } else {
                    Log.e("MVVM", "API request failed with status: ${result.code()}")
                }
            } catch (e: Exception) {
                Log.e("MVVM", "Error fetching meal by id", e)
            }
        }
    }
}