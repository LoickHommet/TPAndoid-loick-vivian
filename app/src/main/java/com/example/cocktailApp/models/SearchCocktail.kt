package com.example.cocktailApp.models


import com.google.gson.annotations.SerializedName

data class SearchCocktail(
    @SerializedName("drinks")
    val drinks: List<Drink>
)