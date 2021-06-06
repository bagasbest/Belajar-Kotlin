package com.bagasbest.beoskop21.model.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity

@Dao
interface BeOskopDao {

    // Movie Dao

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Update
    fun updateMovies(movie: MovieEntity)

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovieList(query: SimpleSQLiteQuery)
            : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE isFavoriteMovies = 1")
    fun getFavoriteMovieList(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>


    // Series Dao

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>)

    @Update
    fun updateSeries(series: SeriesEntity)

    @RawQuery(observedEntities = [SeriesEntity::class])
    fun getSeriesList(query: SimpleSQLiteQuery)
            : DataSource.Factory<Int, SeriesEntity>

    @Query("SELECT * FROM series WHERE isFavoriteSeries = 1")
    fun getFavoriteSeriesList(): DataSource.Factory<Int, SeriesEntity>

    @Query("SELECT * FROM series WHERE id = :id")
    fun getSeriesById(id: Int): LiveData<SeriesEntity>
}