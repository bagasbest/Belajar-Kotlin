package com.bagasbest.fundamental2.myViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyViewModelMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.lang.Exception

class MyViewModelMainActivity : AppCompatActivity() {

    private lateinit var adapter : WeatherAdapter
    private lateinit var binding: ActivityMyViewModelMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyViewModelMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        binding.rvUserList.adapter = adapter

        binding.btnCity.setOnClickListener {
            val city = binding.editCity.text.toString()
            if (city.isEmpty()) return@setOnClickListener
            showLoading(true)
            setWeather(city)
        }

    }

    private fun setWeather(cities: String) {
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

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.viewModelProgressBar.visibility = View.VISIBLE
        } else {
            binding.viewModelProgressBar.visibility = View.GONE
        }
    }
}