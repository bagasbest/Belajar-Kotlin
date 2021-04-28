package com.bagasbest.belaundry.viewmodel.viewmodel

import android.util.Log
import com.bagasbest.belaundry.model.api.RetroInstance
import com.bagasbest.belaundry.model.api.RetroService
import com.bagasbest.belaundry.model.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDataViewModel {

    fun addUser(
        name: String,
        address: String,
        phone: String,
        price: String
    ) {
        Log.d("TAG", "$name $address")

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        //val saveData = retroInstance.createLaundryData(name, address, phone, price)

//        saveData.enqueue(object : Callback<ResponseModel> {
//            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
//                if (response.isSuccessful) {
//                    Log.d(TAG, "Data berhasil ditambahkan")
//                } else {
//                    Log.d(TAG, "Data tidak berhasil ditambahkan")
//                }
//
//            }
//
//            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//                Log.d(TAG, "Gafal")
//            }
//
//        })
    }

}