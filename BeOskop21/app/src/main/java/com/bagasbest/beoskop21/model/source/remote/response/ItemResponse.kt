package com.bagasbest.beoskop21.model.source.remote.response

import com.google.gson.annotations.SerializedName

data class ItemResponse<T>(
    @SerializedName("results")
    val results: List<T>? = null,
)