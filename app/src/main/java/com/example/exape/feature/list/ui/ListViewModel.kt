package com.example.exape.feature.list.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exape.feature.list.model.CharacterModel
import com.example.exape.feature.list.remote.RickAndMortyRepository
import com.example.exape.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {
    private var curPage = 1

    var characterList = mutableStateOf<List<CharacterModel>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init{
        callApi()
    }

    fun callApi() {
        if(isLoading.value){
            return
        }
        isLoading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            val result = repository.getCharacters(curPage.toString())
            when(result) {
                is Resource.Success -> {
                    curPage++
                    loadError.value = ""
                    isLoading.value = false
                    characterList.value += result.data!!.results
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

}