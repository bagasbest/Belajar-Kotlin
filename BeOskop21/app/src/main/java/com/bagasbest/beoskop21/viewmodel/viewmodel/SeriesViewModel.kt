package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.vo.Resource

class SeriesViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun tvSeries(sort: String): LiveData<Resource<PagedList<SeriesEntity>>> = dataRepository.getSeriesList(sort)
    fun setFavoriteSeries(series: SeriesEntity, status: Boolean) = dataRepository.setFavoriteSeries(series, status)

}