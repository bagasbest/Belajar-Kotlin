package com.bagasbest.beoskop21.model.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.source.local.room.BeOskopDao
import com.bagasbest.beoskop21.model.utils.SortUtils

class LocalDataSource constructor(private val beoskopDao: BeOskopDao) {

    companion object {
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(beoskopDao: BeOskopDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(beoskopDao)
    }


     // For Movie

    fun getMovieList(sort: String) : DataSource.Factory<Int, MovieEntity> =
        beoskopDao.getMovieList(SortUtils.getSortedQuery(sort, SortUtils.MOVIES_TB))

    fun getMovieById(id: Int) : LiveData<MovieEntity> =
        beoskopDao.getMovieById(id)

    fun getFavoriteMovieList() : DataSource.Factory<Int, MovieEntity> =
        beoskopDao.getFavoriteMovieList()

    fun setFavoriteMovie(movie: MovieEntity, status: Boolean) {
        movie.isFavorite = status
        beoskopDao.updateMovies(movie)
    }

    fun insertMovies(id: List<MovieEntity>) =
        beoskopDao.insertMovies(id)

    fun updateMovies(movie: MovieEntity) =
        beoskopDao.updateMovies(movie)




    // For Series

    fun getSeriesList(sort: String) : DataSource.Factory<Int, SeriesEntity> =
        beoskopDao.getSeriesList(SortUtils.getSortedQuery(sort, SortUtils.SERIES_TB))

    fun getSeriesById(id: Int) : LiveData<SeriesEntity> =
        beoskopDao.getSeriesById(id)

    fun getFavoriteSeriesList() : DataSource.Factory<Int, SeriesEntity> =
        beoskopDao.getFavoriteSeriesList()

    fun setFavoriteSeries(series: SeriesEntity, status: Boolean) {
        series.isFavorite = status
        beoskopDao.updateSeries(series)
    }

    fun insertSeries(id: List<SeriesEntity>) =
        beoskopDao.insertSeries(id)

    fun updateSeries(series: SeriesEntity) =
        beoskopDao.updateSeries(series)


}