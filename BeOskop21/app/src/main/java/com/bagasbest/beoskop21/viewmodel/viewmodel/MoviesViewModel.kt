package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.response.ItemList

class MoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun movie(): LiveData<List<ItemList>> = dataRepository.getMovie()
}