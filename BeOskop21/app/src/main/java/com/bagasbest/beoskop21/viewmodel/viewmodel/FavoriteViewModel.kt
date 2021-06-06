package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity

class FavoriteViewModel(private val dataRepository: DataRepository) : ViewModel() {
    fun getFavoriteMovies() = dataRepository.getFavoriteMovies()
    fun setFavoriteMovies(movie: MovieEntity) {
        val newState = !movie.isFavorite
        dataRepository.setFavoriteMovies(movie, newState)
    }


    fun getFavoriteSeries() = dataRepository.getFavoriteSeries()
    fun setFavoriteSeries(series: SeriesEntity) {
        val newState = !series.isFavorite
        dataRepository.setFavoriteSeries(series, newState)
    }

}