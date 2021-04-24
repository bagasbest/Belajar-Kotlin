package com.bagasbest.beoskop21.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesModel(
    var title: String? = null,
    var poster: Int? = 0,
    var year: Int? = 0,
    var genre: String? = null,
    var durationEpisode: String? = null,
    var pgRating: String? = null,
    var userScore: Int? = 0,
    var description: String? = null,
    var creator: String? = null,
    var streamingOn: String? = null,
) : Parcelable