package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.vo.Resource

class DetailViewModel (private val dataRepository: DataRepository) : ViewModel() {

    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>
    private lateinit var detailSeries: LiveData<Resource<SeriesEntity>>

    fun getMovieDetail(movieId: Int) {
        detailMovie = dataRepository.getMovieDetail(movieId)
    }

    fun setFavoriteMovie() {
        val resources = detailMovie.value
        if(resources?.data != null) {
            val newState = !resources.data.isFavorite
            dataRepository.setFavoriteMovies(resources.data, newState)
        }
    }

    fun getTvSeriesDetail(tvSeriesId: Int) {
        detailSeries = dataRepository.getTvSeriesDetail(tvSeriesId)
    }

    fun setFavoriteSeries() {
        val resources = detailSeries.value
        if(resources?.data != null) {
            val newState = !resources.data.isFavorite
            dataRepository.setFavoriteSeries(resources.data, newState)
        }
    }

    fun getDetailMovie() = detailMovie
    fun getDetailSeries() = detailSeries

}