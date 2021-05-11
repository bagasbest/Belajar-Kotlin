package com.bagasbest.beoskop21.model.source

import androidx.lifecycle.LiveData
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.model.SeriesModel

interface MovieSeriesDataSource {

    fun getAllMovie(): LiveData<List<MovieModel>>

    fun getAllSeries(): LiveData<List<SeriesModel>>

    fun getDetailMovie(): LiveData<MovieModel>

    fun getDetailSeries(): LiveData<SeriesModel>

}