package com.example.cocktailApp.api

import com.example.cocktailApp.models.Drink
import com.example.cocktailApp.models.SearchCocktail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinkService {
    @GET("search.php")
    suspend fun getSearchedDrinks(
        @Query("s") query: String?
    ) : Response<SearchCocktail>

    @GET("lookup.php")
    suspend fun getDetailsDrink(
        @Path("i") plantId: String
    ) : Response<Drink>

    @GET("random.php")
    suspend fun getRandomDrink() : Response<SearchCocktail>
}