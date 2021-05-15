package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ItemMoviesBinding
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.view.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies: List<ItemList> = emptyList()

    fun setData(movies: List<ItemList>?) {
        if(movies == null) return
        this.listMovies = movies
        notifyDataSetChanged()
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
        fun bind(movie: ItemList) {
            with(binding) {
                tvMovieTitle.text = movie.title
                tvMovieLaunchDate.text = movie.launchDate
                tvMovieDescription.text = movie.overview
                tvMoviesRating.text = movie.userScore.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgMoviePoster)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TITLE, movie.title)
                    intent.putExtra(DetailActivity.ITEM_ID, movie.id.toString())
                    intent.putExtra(DetailActivity.CATALOGUE, "Movies")
                    intent.putExtra(DetailActivity.IMAGE, "https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    it.context.startActivity(intent)
                }

            }
        }

    }

}