package com.bagasbest.beoskop21.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ActivityHomeBinding
import com.bagasbest.beoskop21.viewmodel.adapter.SectionPagerAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.home_title)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f


    }
}