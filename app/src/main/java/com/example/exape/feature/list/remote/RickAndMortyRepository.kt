package com.example.exape.feature.list.remote

import com.example.exape.feature.list.model.ApiResponse
import com.example.exape.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RickAndMortyRepository @Inject constructor (private val api: RickAndMotyApi) {

    suspend fun getCharacters(page: String): Resource<ApiResponse> {
        val response = try {
            api.getCharacters(page)
        } catch(e: Exception) {
            return Resource.Error("Um erro inesperado ocorreu")
        }
        return Resource.Success(response)
    }
}