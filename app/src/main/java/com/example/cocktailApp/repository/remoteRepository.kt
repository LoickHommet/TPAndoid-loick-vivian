package com.example.cocktailApp.repository

import android.util.Log
import com.example.cocktailApp.api.ApiHelper
import com.example.cocktailApp.api.ApiResult
import com.example.cocktailApp.api.safeApiCall
import com.example.cocktailApp.models.Drink
import com.example.cocktailApp.models.SearchCocktail
import retrofit2.Response

object RemoteRepository {

    suspend fun getSearchedDrinks(query: String?): ApiResult<SearchCocktail> = safeApiCall {
        ApiHelper.retrofitClient.getSearchedDrinks(query)
    }
    suspend fun getDetailDrink(plantId: String): ApiResult<Drink> = safeApiCall {
        ApiHelper.retrofitClient.getDetailsDrink(plantId)
    }
    suspend fun getRandomDrink(): ApiResult<SearchCocktail>  = safeApiCall {
        ApiHelper.retrofitClient.getRandomDrink()
    }
}
