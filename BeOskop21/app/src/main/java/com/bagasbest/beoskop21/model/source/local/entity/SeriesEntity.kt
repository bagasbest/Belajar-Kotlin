package com.bagasbest.beoskop21.model.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SeriesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "first_air_date")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "vote_average")
    var voteAverage: String? = null,

    @ColumnInfo(name = "vote_count")
    var voteCount: String? = null,

    @ColumnInfo(name = "isFavoriteSeries")
    var isFavorite: Boolean = false
)