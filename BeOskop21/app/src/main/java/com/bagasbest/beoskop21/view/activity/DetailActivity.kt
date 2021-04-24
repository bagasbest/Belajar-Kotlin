package com.bagasbest.beoskop21.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ActivityDetailBinding
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.model.model.SeriesModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.DetailViewModel
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ITEMS = "extra_items"
        const val TITLE = "title"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subtitle = intent.getStringExtra(TITLE)
        supportActionBar?.title = resources.getString(R.string.detail_title) + subtitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val itemTitle = extras.getString(EXTRA_ITEMS)
            if (itemTitle != null) {
                viewModel.setSelectedItems(itemTitle)
                if(subtitle == "Movies") {
                    populateMovie(viewModel.getItemMovie())
                } else if (subtitle == "Series") {
                    populateSeries(viewModel.getItemSeries())
                }
            }
        }
    }

    private fun populateMovie(movie: MovieModel) {
        binding.detailTitle.text = movie.title
        binding.detailLaunchDate.text = movie.launchDate
        binding.detailDuration.text = movie.duration
        binding.detailPgRating.text = movie.pgRating
        binding.detailUserScore.text = movie.userScore.toString()
        binding.creator.text = movie.director
        binding.streaming.visibility = View.INVISIBLE
        binding.detailGenre.visibility = View.GONE

        Glide.with(this)
            .load(movie.poster)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.detailPoster)
    }

    private fun populateSeries(series: SeriesModel) {
        binding.detailTitle.text = series.title
        binding.detailLaunchDate.text = series.year.toString()
        binding.detailDuration.text = series.durationEpisode
        binding.detailPgRating.text = series.pgRating
        binding.detailUserScore.text = series.userScore.toString()
        binding.creator.text = series.creator
        binding.streaming.text = series.streamingOn
        binding.detailGenre.text = series.genre

        Glide.with(this)
            .load(series.poster)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(binding.detailPoster)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}