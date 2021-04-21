package com.bagasbest.beoskop21.model.model

data class MovieModel(
    var title: String? = null,
    var poster: Int = 0,
    var launchDate: String? = null,
    var duration: String? = null,
    var pgRating: String? = null,
    var userScore: Int? = 0,
    var description: String? = null,
    var director: String? = null,
)