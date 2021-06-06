package com.bagasbest.beoskop21.model.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.vo.Resource

interface DataSource {
    // MOVIE
    fun getMovieList(sort: String) : LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(movieId: Int) : LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies() : LiveData<PagedList<MovieEntity>>
    fun setFavoriteMovies(movie: MovieEntity, status: Boolean)

    // SERIES
    fun getSeriesList(sort: String) : LiveData<Resource<PagedList<SeriesEntity>>>
    fun getTvSeriesDetail(tvSeriesId: Int) : LiveData<Resource<SeriesEntity>>
    fun getFavoriteSeries() : LiveData<PagedList<SeriesEntity>>
    fun setFavoriteSeries(series: SeriesEntity, status: Boolean)
}