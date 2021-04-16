package com.bagasbest.fundamental2.mySettingPref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.ActivityMySettingPrefMainBinding

class MySettingPrefMainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMySettingPrefMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMySettingPrefMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()


    }
}