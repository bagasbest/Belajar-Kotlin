package com.bagasbest.beoskop21.view.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ActivityDetailBinding
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail
import com.bagasbest.beoskop21.viewmodel.viewmodel.DetailViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val TITLE = "title"
        const val ITEM_ID = "item_id"
        const val CATALOGUE = "catalogue"
        const val IMAGE = "image"
    }

    private val movieDetailViewModel by lazy {
        val factory = ViewModelFactory.getInstance()
        factory?.let { ViewModelProvider(this, it) }?.get(DetailViewModel::class.java)
    }

    private val tvSeriesViewModel by lazy {
        val factory = ViewModelFactory.getInstance()
        factory?.let { ViewModelProvider(this, it) }?.get(DetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subtitle = intent.getStringExtra(TITLE)
        val catalogue = intent.getStringExtra(CATALOGUE)
        supportActionBar?.title = subtitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(catalogue == "Movies") {
            binding.progressBar.visibility = View.VISIBLE
            movieDetailViewModel?.getMovieDetail(intent.getStringExtra(ITEM_ID)!!)
                ?.observe(this, {
                    binding.progressBar.visibility = View.GONE
                    populateMovie(it)
                })
        }
        else {
            binding.progressBar.visibility = View.VISIBLE
            tvSeriesViewModel?.getTvSeriesDetail(intent.getStringExtra(ITEM_ID)!!)
                ?.observe(this, {
                    binding.progressBar.visibility = View.GONE
                    populateSeries(it)
                })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateMovie(movie: ItemList?) {
        binding.detailLaunchDate.text = resources.getString(R.string.launch_date) + movie?.launchDate
        binding.detailPgRating.text = resources.getString(R.string.pg_rating) + movie?.userScore.toString()
        binding.detailUserScore.text = resources.getString(R.string.user_score) + movie?.voteCount.toString()
        binding.detailDescription.text = movie?.overview

        Glide.with(this)
            .load(intent.getStringExtra(IMAGE))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.detailPoster)
    }

    @SuppressLint("SetTextI18n")
    private fun populateSeries(series: TvSeriesDetail) {
        binding.detailLaunchDate.text = resources.getString(R.string.launch_date) +series.firstAirDate
        binding.detailPgRating.text = resources.getString(R.string.pg_rating) + series.voteAverage.toString()
        binding.detailUserScore.text =  resources.getString(R.string.user_score) + series.voteCount.toString()
        binding.detailDescription.text = series.overview

        Glide.with(this)
            .load(intent.getStringExtra(IMAGE))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.detailPoster)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}