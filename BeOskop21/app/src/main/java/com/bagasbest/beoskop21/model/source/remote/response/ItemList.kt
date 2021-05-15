package com.bagasbest.beoskop21.model.source.remote.response

import com.google.gson.annotations.SerializedName

data class ItemList(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val launchDate: String?,

    @SerializedName("vote_average")
    val userScore: Double?,

    @SerializedName("vote_count")
    val voteCount: Int,


    //for TvSeries
    @SerializedName("name")
    val name: String?,

    @SerializedName("first_air_date")
    val firstAirDate: String?

)