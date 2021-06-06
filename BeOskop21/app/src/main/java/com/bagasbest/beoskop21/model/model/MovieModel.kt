package com.bagasbest.beoskop21.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    var id: Int? = 0,
    var title: String? = null,
    var poster: String? = null,
    var launchDate: String? = null,
    var duration: String? = null,
    var pgRating: String? = null,
    var userScore: String? = null,
    var voteCount: String? = null,
    var description: String? = null,
    var director: String? = null,
) : Parcelable