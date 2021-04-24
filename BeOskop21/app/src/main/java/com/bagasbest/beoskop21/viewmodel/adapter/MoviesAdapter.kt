package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ItemMoviesBinding
import com.bagasbest.beoskop21.model.model.MovieModel
import com.bagasbest.beoskop21.view.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies = ArrayList<MovieModel>()

    fun setData(movies: List<MovieModel>?) {
        if(movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = this.listMovies.size

    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel) {
            with(binding) {
                tvMovieTitle.text = movie.title
                tvMovieLaunchDate.text = movie.launchDate
                tvMovieDescription.text = movie.description
                tvMoviesRating.text = movie.userScore.toString()
                Glide.with(itemView.context)
                    .load(movie.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgMoviePoster)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ITEMS, movie.title)
                    intent.putExtra(DetailActivity.TITLE, "Movies")
                    it.context.startActivity(intent)
                }

            }
        }

    }

}