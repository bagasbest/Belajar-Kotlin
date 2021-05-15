package com.bagasbest.beoskop21.model.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bagasbest.beoskop21.BuildConfig
import com.bagasbest.beoskop21.model.network.ApiConfig
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.ItemResponse
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail
import com.bagasbest.beoskop21.model.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(apiConfig: ApiConfig) {
    private val apiKey = BuildConfig.API_KEY
    private var handler = Handler(Looper.getMainLooper())
    private val apiConfig = ApiConfig

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private val TAG = RemoteDataSource::class.java.simpleName

        @Volatile
        private var instance: RemoteDataSource? = null

        /// Di sini terdapat method getInstance yang berfungsi untuk membuat kelas RemoteDataSource sebagai Singleton. Fungsinya yaitu supaya tercipta satu instance saja di dalam JVM.

        fun getInstance(apiConfig: ApiConfig): RemoteDataSource {
            if (instance == null)
                instance = RemoteDataSource(apiConfig)
            return instance!!
        }

    }

    fun getMovie(getMovieCallback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create()
                .getMovie(apiKey)
                .enqueue(object : Callback<ItemResponse>{
                    override fun onResponse(
                        call: Call<ItemResponse>,
                        response: Response<ItemResponse>
                    ) {
                        response.body()?.results?.let { getMovieCallback.onResponse(it) }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                        Log.d(TAG, t.printStackTrace().toString())
                    }
                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieDetail (movieId: String, getMovieDetailCallback: GetMovieDetailCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create()
                .getMovieDetail(movieId, apiKey)
                .enqueue(object : Callback<ItemList> {
                    override fun onResponse(call: Call<ItemList>, response: Response<ItemList>) {
                        getMovieDetailCallback.onResponse(response.body()!!)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ItemList>, t: Throwable) {
                        Log.d(TAG, t.printStackTrace().toString())
                    }
                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvSeries (getTvSeriesCallback: GetTvSeriesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create()
                .getTvSeries(apiKey)
                .enqueue(object : Callback<ItemResponse> {
                    override fun onResponse(
                        call: Call<ItemResponse>,
                        response: Response<ItemResponse>
                    ) {
                        response.body()?.results?.let { getTvSeriesCallback.onResponse(it) }
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                        Log.d(TAG, t.printStackTrace().toString())
                    }
                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvSeriesDetail (tvSeriesId: String, getTvSeriesDetailCallback: GetTvSeriesDetailCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            apiConfig.create()
                .getTvSeriesDetail(tvSeriesId, apiKey)
                .enqueue(object : Callback<TvSeriesDetail> {
                    override fun onResponse(
                        call: Call<TvSeriesDetail>,
                        response: Response<TvSeriesDetail>
                    ) {
                        getTvSeriesDetailCallback.onResponse(response.body()!!)
                        EspressoIdlingResource.decrement()
                    }

                    override fun onFailure(call: Call<TvSeriesDetail>, t: Throwable) {
                        Log.d(TAG, t.printStackTrace().toString())
                    }
                })
        }, SERVICE_LATENCY_IN_MILLIS)
    }


    interface GetMovieCallback {
        fun onResponse(movieResponse: List<ItemList>)
    }

    interface GetMovieDetailCallback {
        fun onResponse(movieResponse: ItemList)
    }

    interface GetTvSeriesCallback {
        fun onResponse(tvSeriesResponse: List<ItemList>)
    }

    interface GetTvSeriesDetailCallback {
        fun onResponse(tvSeriesDetailResponse: TvSeriesDetail)
    }



}