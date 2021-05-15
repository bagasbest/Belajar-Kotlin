package com.bagasbest.beoskop21.model.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bagasbest.beoskop21.model.source.remote.RemoteDataSource
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail

class DataRepository(private val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): DataRepository? {
            if (instance == null) {
                synchronized(DataRepository::class.java) {
                    if (instance == null) {
                        instance = DataRepository(remoteDataSource)
                    }
                }
            }
            return instance
        }
    }

    override fun getMovie(): LiveData<List<ItemList>> {
        val listOfMovie = MutableLiveData<List<ItemList>>()
        remoteDataSource.getMovie(object : RemoteDataSource.GetMovieCallback {
            override fun onResponse(movieResponse: List<ItemList>) {
                listOfMovie.postValue(movieResponse)
            }
        })
        return listOfMovie
    }

    override fun getMovieDetail(movieId: String): LiveData<ItemList> {
        val movieDetail = MutableLiveData<ItemList>()
        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.GetMovieDetailCallback {
            override fun onResponse(movieResponse: ItemList) {
                movieDetail.postValue(movieResponse)
            }
        })
        return movieDetail
    }

    override fun getTvSeries(): LiveData<List<ItemList>> {
        val listOfTvSeries = MutableLiveData<List<ItemList>>()
        remoteDataSource.getTvSeries(object : RemoteDataSource.GetTvSeriesCallback {
            override fun onResponse(tvSeriesResponse: List<ItemList>) {
                listOfTvSeries.postValue(tvSeriesResponse)
            }
        })
        return listOfTvSeries
    }

    override fun getTvSeriesDetail(tvSeriesId: String): LiveData<TvSeriesDetail> {
        val tvSeriesDetail = MutableLiveData<TvSeriesDetail>()
        remoteDataSource.getTvSeriesDetail(
            tvSeriesId,
            object : RemoteDataSource.GetTvSeriesDetailCallback {
                override fun onResponse(tvSeriesDetailResponse: TvSeriesDetail) {
                    tvSeriesDetail.postValue(tvSeriesDetailResponse)
                }
            })
        return tvSeriesDetail
    }
}