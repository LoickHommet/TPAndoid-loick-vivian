package com.example.cocktailApp.api

import android.util.Log
import retrofit2.Response

sealed class ApiResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val errorResponse: String) : ApiResult<Nothing>()
}
suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>): ApiResult<T> {
    val myResp: Response<T> = call()
    return when {
        myResp.isSuccessful -> ApiResult.Success(myResp.body()!!)
        else -> ApiResult.Error("Error !")
    }
}