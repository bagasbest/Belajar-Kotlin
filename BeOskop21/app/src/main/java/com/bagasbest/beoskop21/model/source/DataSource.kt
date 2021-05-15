package com.bagasbest.beoskop21.model.source

import androidx.lifecycle.LiveData
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail

interface DataSource {
    fun getMovie() : LiveData<List<ItemList>>
    fun getMovieDetail(movieId: String) : LiveData<ItemList>
    fun getTvSeries() : LiveData<List<ItemList>>
    fun getTvSeriesDetail(tvSeriesId: String) : LiveData<TvSeriesDetail>
}