package com.example.cocktailApp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktailApp.api.ApiResult
import com.example.cocktailApp.models.SearchCocktail
import com.example.cocktailApp.repository.RemoteRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    var searchedDrinkLiveData = MutableLiveData<SearchCocktail>()
    var apiErrorLiveData = MutableLiveData<ApiResult.Error>()

    fun getDrinks(query: String?) {
        viewModelScope.launch {
            when(val result = RemoteRepository.getSearchedDrinks(query)){
                is ApiResult.Success -> searchedDrinkLiveData.postValue(result.data!!)
                is ApiResult.Error -> apiErrorLiveData.postValue(result)
            }
        }
    }
}