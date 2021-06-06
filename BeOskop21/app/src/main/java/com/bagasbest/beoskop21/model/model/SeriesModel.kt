package com.bagasbest.beoskop21.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesModel(
    var id: Int? = 0,
    var title: String? = null,
    var poster: String? = null,
    var onAir: String? = null,
    var genre: String? = null,
    var durationEpisode: String? = null,
    var pgRating: String? = null,
    var userScore: String? = null,
    var voteCount: String? = null,
    var description: String? = null,
    var creator: String? = null,
    var streamingOn: String? = null,
) : Parcelable