package com.example.exape.feature.list.remote

import com.example.exape.feature.list.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMotyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: String
    ): ApiResponse
}