package com.bagasbest.fundamental2.myService

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyServiceMainBinding

class MyServiceMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyServiceMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyServiceMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener {
            val mStartServiceIntent = Intent(this, MyService::class.java)
            startService(mStartServiceIntent)
        }

        binding.startJobIntentService.setOnClickListener {

        }

        binding.btnStartBoundService.setOnClickListener {

        }

        binding.btnStopBoundService.setOnClickListener {

        }
    }
}