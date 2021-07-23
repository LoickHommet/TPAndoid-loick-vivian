package com.example.cocktailApp.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper{

    const val BASE_URL = "https://thecocktaildb.com/api/json/v1/1/"

    val retrofitClient: DrinkService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(DrinkService::class.java)
    }
    private val okHttpClient = OkHttpClient()
        .newBuilder()
        .build()
}