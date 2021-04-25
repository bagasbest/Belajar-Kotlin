package com.bagasbest.beoskop21.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ActivityAboutDeveloperBinding

class AboutDeveloperActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutDeveloperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutDeveloperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.about_developer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}