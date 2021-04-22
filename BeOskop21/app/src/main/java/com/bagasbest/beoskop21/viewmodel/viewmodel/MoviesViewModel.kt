package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.utils.DummyData

class MoviesViewModel : ViewModel() {

    fun getMovies() : List<MovieModel> = DummyData.generateDummyMovie()

}