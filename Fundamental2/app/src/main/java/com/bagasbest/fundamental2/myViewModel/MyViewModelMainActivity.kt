package com.bagasbest.fundamental2.myViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyViewModelMainBinding
import com.bagasbest.fundamental2.myWorkManager.Main
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat

class MyViewModelMainActivity : AppCompatActivity() {

    private lateinit var adapter : WeatherAdapter
    private lateinit var mainViewModel : MainViewModel
    private lateinit var binding: ActivityMyViewModelMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyViewModelMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()

        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        binding.rvUserList.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.btnCity.setOnClickListener {
            val city = binding.editCity.text.toString()
            if (city.isEmpty()) return@setOnClickListener
            showLoading(true)
            mainViewModel.setWeather(city)
        }

        mainViewModel.getWeathers().observe(this, { weatherModel ->
            if(weatherModel != null) {
                adapter.setData(weatherModel)
                showLoading(false)
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