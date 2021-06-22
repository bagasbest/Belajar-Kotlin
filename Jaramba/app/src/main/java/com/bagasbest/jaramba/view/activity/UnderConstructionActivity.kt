package com.bagasbest.jaramba.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityUnderConstructionBinding
import com.bumptech.glide.Glide

class UnderConstructionActivity : AppCompatActivity() {

    private var binding: ActivityUnderConstructionBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnderConstructionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.imageView2?.let {
            Glide.with(this)
                .load(R.drawable.under_construction)
                .into(it)
        }

        binding?.backButton?.setOnClickListener {
           onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}