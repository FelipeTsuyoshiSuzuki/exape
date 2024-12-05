package com.example.exape.feature.list.model

import com.google.gson.annotations.SerializedName

data class InfoModel(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)
