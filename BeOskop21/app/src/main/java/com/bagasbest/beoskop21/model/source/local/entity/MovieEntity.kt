package com.bagasbest.beoskop21.model.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: String? = null,

    @ColumnInfo(name = "vote_count")
    var voteCount: String? = null,

    @ColumnInfo(name = "isFavoriteMovies")
    var isFavorite: Boolean = false
)