package com.bagasbest.belaundry.model.api

import com.bagasbest.belaundry.model.model.ResponseModel
import retrofit2.Call
import retrofit2.http.*


interface RetroService {

    @GET("retrieve.php")
    fun retrieveLaundryData(): Call<ResponseModel>

    @FormUrlEncoded
    @POST("create.php")
    fun createLaundryData(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("phone") phone: String,
        @Field("price") price: String
    ) : Call<ResponseModel>

}