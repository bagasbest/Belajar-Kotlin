package com.bagasbest.fundamental2.liveDataWithAPI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.ActivityLiveDataWithAPIMainBinding
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class LiveDataWithAPIMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLiveDataWithAPIMainBinding
    private val mainViewModel: LiveDataWithApiMainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataWithAPIMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        observeMainViewModel()


        binding.btnSend.setOnClickListener { it ->
            mainViewModel.postReview(binding.edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

    }

    private fun observeMainViewModel() {
        mainViewModel.restaurant.observe(this, { it ->
            binding.tvTitle.text = it.name
            binding.tvDescription.text = it.description
            Glide.with(this)
                .load("https://restaurant-api.dicoding.dev/images/large/${it.pictureId}")
                .into(binding.ivPicture)
        })

        mainViewModel.listReview.observe(this, { it ->
            val listReview = it.map {
                "${it.review}\n- ${it.name}"
            }

            binding.lvReview.adapter = ArrayAdapter(this, R.layout.item_review, listReview)
            binding.edReview.setText("")
        })

        mainViewModel.isLoading.observe(this, {
            binding.progressBar.visibility =
                if (it) android.view.View.VISIBLE else android.view.View.GONE
        })

        mainViewModel.snackbarText.observe(this, {
            it.getContentIfNotHandled()?.let { snackbarText ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackbarText,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })


    }

}