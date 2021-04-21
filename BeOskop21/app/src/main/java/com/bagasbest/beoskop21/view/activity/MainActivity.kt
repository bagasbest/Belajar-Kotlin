package com.bagasbest.beoskop21.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    companion object {
        private const val SPLASH_SCREEN = 3500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // animation
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val botAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        // hooks
        binding.icon.animation = topAnim
        binding.description.animation = botAnim
        binding.version.animation = botAnim

        loadSplashScreen()
    }

    private fun loadSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}