package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ItemMoviesBinding
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.view.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class MoviesAdapter : PagedListAdapter<MovieEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }


    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvMovieTitle.text = movie.title
                tvMovieLaunchDate.text = movie.releaseDate
                tvMovieDescription.text = movie.overview
                tvMoviesRating.text = movie.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgMoviePoster)

                if(movie.isFavorite) {
                    favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }


                itemView.setOnClickListener {

                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TITLE, "Movies Detail")
                    intent.putExtra(DetailActivity.TITLE, movie.title)
                    intent.putExtra(DetailActivity.ITEM_ID, movie.id.toString())
                    intent.putExtra(DetailActivity.CATALOGUE, "Movies")
                    intent.putExtra(
                        DetailActivity.IMAGE,
                        "https://image.tmdb.org/t/p/w500${movie.posterPath}"
                    )
                    it.context.startActivity(intent)
                }

                favoriteBtn.setOnClickListener {
                    if(movie.isFavorite) {
                        favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    } else {
                        favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                   onItemClickCallBack.clickFavoriteButton(movie)
                }
            }
        }
    }

    interface OnItemClickCallBack {
        fun clickFavoriteButton(movie: MovieEntity)
    }
}