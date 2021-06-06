package com.bagasbest.beoskop21.model.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("release_date")
    val launchDate: String? = null,

    @SerializedName("vote_average")
    val userScore: Double? = 0.0,

    @SerializedName("vote_count")
    val voteCount: Int? = 0,
)