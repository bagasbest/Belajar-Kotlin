package com.bagasbest.fundamental2.academy.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.fundamental2.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f


    }
}