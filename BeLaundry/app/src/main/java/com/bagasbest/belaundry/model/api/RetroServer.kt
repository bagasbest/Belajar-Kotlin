package com.bagasbest.belaundry.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroServer {
    companion object {
        const val baseURL = "http://10.0.2.2/laundry/"
    }

    var retro: Retrofit? = null


    fun konekRetrofit(): Retrofit? {
        if(retro == null) {
            retro = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retro
    }
}