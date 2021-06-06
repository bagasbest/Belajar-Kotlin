package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity

class MoviesViewModel (private val dataRepository: DataRepository) : ViewModel() {

    fun movie(sort: String) = dataRepository.getMovieList(sort)
    fun setFavoriteMovie(movie: MovieEntity, status: Boolean) = dataRepository.setFavoriteMovies(movie, status)
}