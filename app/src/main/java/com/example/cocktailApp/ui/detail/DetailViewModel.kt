package com.example.cocktailApp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailApp.api.ApiResult
import com.example.cocktailApp.models.SearchCocktail
import com.example.cocktailApp.repository.RemoteRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    var detailDrinkLiveData = MutableLiveData<SearchCocktail>()
    var apiErrorLiveData = MutableLiveData<ApiResult.Error>()

    fun getDetailDrink(idDrink: String) {
        viewModelScope.launch {
            when(val result = RemoteRepository.getDetailDrink(idDrink)){
                is ApiResult.Success -> detailDrinkLiveData.postValue(result.data)
                is ApiResult.Error -> apiErrorLiveData.postValue(result)
            }
        }
    }
}