package com.bagasbest.beoskop21.model.source.remote


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bagasbest.beoskop21.BuildConfig
import com.bagasbest.beoskop21.model.network.ApiConfig
import com.bagasbest.beoskop21.model.source.remote.response.ItemResponse
import com.bagasbest.beoskop21.model.source.remote.response.MovieResponse
import com.bagasbest.beoskop21.model.source.remote.response.SeriesResponse
import com.bagasbest.beoskop21.model.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    private val apiKey = BuildConfig.API_KEY


    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.simpleName

        fun getInstance(): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RemoteDataSource()
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()

        val responses = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        ApiConfig.create()
            .getMovie(apiKey)
            .enqueue(object : Callback<ItemResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<ItemResponse<MovieResponse>>,
                    response: Response<ItemResponse<MovieResponse>>
                ) {
                    responses.value = ApiResponse.success(
                        response.body()?.results as List<MovieResponse>
                    )
                }

                override fun onFailure(call: Call<ItemResponse<MovieResponse>>, t: Throwable) {
                    Log.e(TAG, "${t.message}")
                }
            })
        EspressoIdlingResource.decrement()
        return responses
    }



    fun getSeries(): LiveData<ApiResponse<List<SeriesResponse>>> {
        EspressoIdlingResource.increment()

        val responses = MutableLiveData<ApiResponse<List<SeriesResponse>>>()

        ApiConfig.create()
            .getTvSeries(apiKey)
            .enqueue(object : Callback<ItemResponse<SeriesResponse>> {
                override fun onResponse(
                    call: Call<ItemResponse<SeriesResponse>>,
                    response: Response<ItemResponse<SeriesResponse>>
                ) {
                    responses.value = ApiResponse.success(
                        response.body()?.results as List<SeriesResponse>
                    )
                }

                override fun onFailure(call: Call<ItemResponse<SeriesResponse>>, t: Throwable) {
                    Log.e(TAG, "${t.message}")
                }
            })
        EspressoIdlingResource.decrement()
        return responses
    }



    fun getDetailMovies(id: Int): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()

        val responses = MutableLiveData<ApiResponse<MovieResponse>>()
        ApiConfig.create()
            .getMovieDetail(id, apiKey)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {

                    responses.value = ApiResponse.success(
                        response.body() as MovieResponse
                    )
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.e(TAG, "${t.message}")
                }
            })
        EspressoIdlingResource.decrement()
        return responses
    }



    fun getDetailSeries(id: Int): LiveData<ApiResponse<SeriesResponse>> {

        val responses = MutableLiveData<ApiResponse<SeriesResponse>>()

        ApiConfig.create()
            .getTvSeriesDetail(id, apiKey)
            .enqueue(object : Callback<SeriesResponse> {
                override fun onResponse(
                    call: Call<SeriesResponse>,
                    response: Response<SeriesResponse>
                ) {
                    responses.value = ApiResponse.success(
                        response.body() as SeriesResponse
                    )
                }

                override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {
                    Log.e(TAG, "${t.message}")
                }
            })
        EspressoIdlingResource.decrement()
        return responses
    }
}