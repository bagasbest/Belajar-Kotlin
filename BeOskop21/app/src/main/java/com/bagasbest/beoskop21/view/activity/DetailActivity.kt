package com.bagasbest.beoskop21.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ActivityDetailBinding
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.vo.Resource
import com.bagasbest.beoskop21.model.vo.Status
import com.bagasbest.beoskop21.viewmodel.viewmodel.DetailViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel


    companion object {
        const val TITLE = "title"
        const val ITEM_ID = "item_id"
        const val CATALOGUE = "catalogue"
        const val IMAGE = "image"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subtitle = intent.getStringExtra(TITLE)
        val catalogue = intent.getStringExtra(CATALOGUE)
        supportActionBar?.title = subtitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]


        if (catalogue == "Movies") {
            viewModel.getMovieDetail(intent.getStringExtra(ITEM_ID)?.toInt()!!)
            viewModel.getDetailMovie().observe(this, { getDetailMovie ->
                when (getDetailMovie.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (getDetailMovie.data != null) {
                            binding.progressBar.visibility = View.GONE
                            populateMovie(getDetailMovie)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e("error", "fail get detail Movie")
                    }
                }
            })
        } else {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getTvSeriesDetail(intent.getStringExtra(ITEM_ID)?.toInt()!!)
            viewModel.getDetailSeries().observe(this, { getDetailSeries ->
                when (getDetailSeries.status) {
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        if (getDetailSeries.data != null) {
                            binding.progressBar.visibility = View.GONE
                            populateSeries(getDetailSeries)
                        }
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e("error", "fail get detail Series")
                    }
                }
            })
        }

        binding.favoriteBtn.setOnClickListener {
            if (catalogue == "Movies") {
                viewModel.setFavoriteMovie()
            } else {
                viewModel.setFavoriteSeries()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateMovie(movie: Resource<MovieEntity>) {
        binding.detailLaunchDate.text =
            resources.getString(R.string.launch_date) + movie.data?.releaseDate
        binding.detailPgRating.text =
            resources.getString(R.string.pg_rating) + movie.data?.voteAverage
        binding.detailUserScore.text =
            resources.getString(R.string.user_score) + movie.data?.voteCount
        binding.detailDescription.text = movie.data?.overview

        Glide.with(this)
            .load(intent.getStringExtra(IMAGE))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.detailPoster)

        if (movie.data?.isFavorite == true) {
            binding.favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun populateSeries(series: Resource<SeriesEntity>) {
        binding.detailLaunchDate.text =
            resources.getString(R.string.launch_date) + series.data?.firstAirDate
        binding.detailPgRating.text =
            resources.getString(R.string.pg_rating) + series.data?.voteAverage
        binding.detailUserScore.text =
            resources.getString(R.string.user_score) + series.data?.voteCount
        binding.detailDescription.text = series.data?.overview

        Glide.with(this)
            .load(intent.getStringExtra(IMAGE))
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.detailPoster)

        if (series.data?.isFavorite == true) {
            binding.favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}