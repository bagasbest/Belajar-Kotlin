package com.bagasbest.fundamental2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.myLocalization.MyLocalizationMainActivity
import com.bagasbest.fundamental2.myTabLayout.MyTabLayoutMainActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnClickListener {
            startActivity(Intent(this, MyNavbar::class.java))
        }

        binding.tabLayout.setOnClickListener {
            startActivity(Intent(this, MyTabLayoutMainActivity::class.java))
        }

        binding.localization.setOnClickListener {
            startActivity(Intent(this, MyLocalizationMainActivity::class.java))
        }

    }
}