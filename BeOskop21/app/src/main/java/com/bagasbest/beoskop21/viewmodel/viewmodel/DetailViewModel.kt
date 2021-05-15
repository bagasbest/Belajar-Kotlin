package com.bagasbest.beoskop21.viewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.model.SeriesModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.ItemResponse
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail
import com.bagasbest.beoskop21.model.utils.DummyData

class DetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getMovieDetail(movieId: String): LiveData<ItemList> = dataRepository.getMovieDetail(movieId)

    fun getTvSeriesDetail(tvSeriesId: String) : LiveData<TvSeriesDetail> = dataRepository.getTvSeriesDetail(tvSeriesId)
}