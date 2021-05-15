package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.utils.DummyData

class MoviesViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val movie: LiveData<List<ItemList>> = dataRepository.getMovie()
}