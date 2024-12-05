package com.example.exape.feature.list.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("info") val info: InfoModel,
    @SerializedName("results") val results: List<CharacterModel>
)