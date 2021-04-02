package com.bagasbest.fundamental2.myDeepNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagasbest.fundamental2.databinding.ActivityMyDeepLinkDetailBinding
import com.bagasbest.fundamental2.databinding.ActivityMyDeepNavigationMainBinding

class MyDeepLinkDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyDeepLinkDetailBinding

    companion object {
        const val EXTRA_TITLE = "title"
        const val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDeepLinkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        binding.tvTitle.text = title
        binding.tvMessage.text = message


    }
}