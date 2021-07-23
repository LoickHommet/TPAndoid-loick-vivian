package com.example.cocktailApp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailApp.api.ApiResult
import com.example.cocktailApp.models.Drink
import com.example.cocktailApp.models.SearchCocktail
import com.example.cocktailApp.repository.RemoteRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var homeDrinkLiveData = MutableLiveData<Drink>()
    var apiErrorLiveData = MutableLiveData<ApiResult.Error>()

    fun getRandomDrink() {
        for (i in 1..3){
            viewModelScope.launch {
                when(val result = RemoteRepository.getRandomDrink()){
                    is ApiResult.Success -> homeDrinkLiveData.postValue(result.data)
                    is ApiResult.Error -> apiErrorLiveData.postValue(result)
                }
            }
        }
    }
}