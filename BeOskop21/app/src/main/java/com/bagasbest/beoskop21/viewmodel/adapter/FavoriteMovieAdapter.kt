package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
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

class FavoriteMovieAdapter : PagedListAdapter<MovieEntity, FavoriteMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val favoriteMovie = getItem(position)
        if(favoriteMovie != null) {
            holder.bind(favoriteMovie)
        }
    }

    fun getSwipedForDelete(swipedPosition: Int) : MovieEntity? = getItem(swipedPosition)

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(list: MovieEntity) {
            with(binding) {
                tvMovieTitle.text = list.title
                tvMovieLaunchDate.text = list.releaseDate
                tvMovieDescription.text = list.overview
                tvMoviesRating.text = list.voteAverage
                favoriteBtn.visibility = View.INVISIBLE

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${list.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgMoviePoster)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TITLE, "Favorite Movie Detail")
                    intent.putExtra(DetailActivity.ITEM_ID, list.id.toString())
                    intent.putExtra(DetailActivity.CATALOGUE, "Movies")
                    intent.putExtra(
                        DetailActivity.IMAGE,
                        "https://image.tmdb.org/t/p/w500${list.posterPath}"
                    )
                    it.context.startActivity(intent)
                }
            }
        }
    }

}