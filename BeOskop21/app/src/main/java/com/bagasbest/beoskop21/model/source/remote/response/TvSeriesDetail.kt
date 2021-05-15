package com.bagasbest.beoskop21.model.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvSeriesDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("first_air_date")
    val firstAirDate: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("poster_path")
    val posterPath: String?,

    )