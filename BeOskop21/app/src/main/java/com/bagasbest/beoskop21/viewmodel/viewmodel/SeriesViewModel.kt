package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.model.SeriesModel
import com.bagasbest.beoskop21.model.utils.DummyData

class SeriesViewModel : ViewModel() {

    fun getSeries() : List<SeriesModel> = DummyData.generateDummyTvSeries()

}