package com.bagasbest.beoskop21.model.source.remote.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("results")
    val results: List<ItemList>
)