package com.bagasbest.belaundry.model.api

import com.bagasbest.belaundry.model.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET


interface RetroService {

    @GET("retrieve.php")
    fun retrieveLaundryData(): Call<ResponseModel>

}