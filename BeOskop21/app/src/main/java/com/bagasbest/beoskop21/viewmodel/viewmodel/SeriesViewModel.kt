package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.model.SeriesModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.utils.DummyData

class SeriesViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val tvSeries: LiveData<List<ItemList>> = dataRepository.getTvSeries()

}