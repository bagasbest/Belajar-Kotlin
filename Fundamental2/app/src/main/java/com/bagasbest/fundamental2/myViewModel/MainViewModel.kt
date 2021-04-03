package com.bagasbest.fundamental2.myViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat

class MainViewModel : ViewModel() {
    val listWeathers = MutableLiveData<ArrayList<WeatherModel>>()

    fun setWeather(cities: String) {
        val listItem = ArrayList<WeatherModel>()

        val apiKey = "1b873eb13024bfacc371a2212f0fa2cf"
        val url = "https://api.openweathermap.org/data/2.5/group?id=${cities}&units=metric&appid=${apiKey}"

        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                try {
                    // parsing json
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("list")

                    for(i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherModel = WeatherModel()
                        weatherModel.id = weather.getInt("id")
                        weatherModel.name = weather.getString("name")
                        weatherModel.currentWeather = weather.getJSONArray("weather").getJSONObject(0).getString("main")
                        weatherModel.description = weather.getJSONArray("weather").getJSONObject(0).getString("description")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelcius = tempInKelvin - 273
                        weatherModel.temperature = DecimalFormat("##.##").format(tempInCelcius)
                        listItem.add(weatherModel)
                    }

                    // set data ke post value
                    listWeathers.postValue(listItem)

                }catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }

        })
    }

    fun getWeathers() : LiveData<ArrayList<WeatherModel>> {
        return listWeathers
    }
}