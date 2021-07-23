package com.example.cocktailApp.repository

import com.example.cocktailApp.api.ApiHelper
import com.example.cocktailApp.api.ApiResult
import com.example.cocktailApp.api.safeApiCall
import com.example.cocktailApp.models.Drink
import com.example.cocktailApp.models.SearchCocktail

object RemoteRepository {

    suspend fun searchPlants(query: String?): ApiResult<SearchCocktail> = safeApiCall {
        ApiHelper.retrofitClient.getSearchedDrinks(query)
    }
    suspend fun getDetailPlant(plantId: String): ApiResult<Drink> = safeApiCall {
        ApiHelper.retrofitClient.getDetailsDrink(plantId)
    }
}
