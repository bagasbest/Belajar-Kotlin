package com.bagasbest.belaundry.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bagasbest.belaundry.R
import com.bagasbest.belaundry.databinding.ActivityMainBinding
import com.bagasbest.belaundry.viewmodel.adapter.AdapterData
import com.bagasbest.belaundry.viewmodel.viewmodel.LaundryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var laundryAdapter: AdapterData
    private lateinit var laundryViewModel: LaundryViewModel

    override fun onResume() {
        super.onResume()
        initRecyclerView()
        initViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.title = resources.getString(R.string.main_title)

        binding.srlData.setOnRefreshListener {
            binding.srlData.isRefreshing = true
            initViewModel()
            binding.srlData.isRefreshing = false
        }
    }

    private fun initViewModel() {
        laundryViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[LaundryViewModel::class.java]

        showLoading(true)
        laundryViewModel.getLaundry().observe(this, {
            if(it != null) {
                laundryAdapter.listData = it.data.toMutableList()
                laundryAdapter.notifyDataSetChanged()
                showLoading(false)
            } else {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
                showLoading(false)
            }
        })
        laundryViewModel.getUsersList()
    }

    private fun initRecyclerView() {
        binding.rvLaundry.apply {
            binding.rvLaundry.layoutManager = LinearLayoutManager(this@MainActivity)
            laundryAdapter = AdapterData()
            adapter = laundryAdapter
        }
    }


    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

}