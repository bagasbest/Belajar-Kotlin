package com.bagasbest.belaundry.viewmodel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.belaundry.model.api.RetroInstance
import com.bagasbest.belaundry.model.api.RetroService
import com.bagasbest.belaundry.model.model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LaundryViewModel : ViewModel() {

    var listLaundry: MutableLiveData<ResponseModel> = MutableLiveData()

    fun getUsersList() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.retrieveLaundryData()
        call.enqueue(object : Callback<ResponseModel> {
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                listLaundry.postValue(null)
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.isSuccessful) {
                    listLaundry.postValue(response.body())
                } else {
                    listLaundry.postValue(null)
                }
            }
        })
    }

    fun getLaundry() : MutableLiveData<ResponseModel> {
        return listLaundry
    }

}