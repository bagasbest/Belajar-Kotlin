package com.bagasbest.beoskop21.model.network

import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.ItemResponse
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/popular")
    fun getMovie(@Query("api_key") apiKey: String): Call<ItemResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: String?, @Query("api_key") apiKey: String) : Call<ItemList>

    @GET("tv/popular")
    fun getTvSeries(@Query("api_key") apiKey: String?) : Call<ItemResponse>

    @GET("tv/{tv_id}")
    fun getTvSeriesDetail(@Path("tv_id") tvId: String?, @Query("api_key") apiKey: String?) : Call<TvSeriesDetail>
}