package com.bagasbest.beoskop21.viewmodel.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.model.SeriesModel
import com.bagasbest.beoskop21.model.utils.DummyData

class DetailViewModel : ViewModel() {

    private lateinit var itemTitle: String

    fun setSelectedItems(itemTitle: String) {
        this.itemTitle = itemTitle
    }

    fun getItemMovie() : MovieModel {
        lateinit var movie: MovieModel
        val movieEntities = DummyData.generateDummyMovie()
        for(mv in movieEntities) {
           if(mv.title == itemTitle) {
               movie = mv
           }
        }
        return movie
    }

    fun getItemSeries() : SeriesModel {
        lateinit var series: SeriesModel
        val seriesEntities = DummyData.generateDummyTvSeries()
        for(se in seriesEntities) {
            if (se.title == itemTitle) {
                series = se
            }
        }
        return series
    }
}