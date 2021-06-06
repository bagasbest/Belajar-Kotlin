package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ItemSeriesBinding
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.view.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteSeriesAdapter : PagedListAdapter<SeriesEntity, FavoriteSeriesAdapter.SeriesViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SeriesEntity>() {
            override fun areItemsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SeriesEntity, newItem: SeriesEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val favoriteSeries = getItem(position)
        if(favoriteSeries != null) {
            holder.bind(favoriteSeries)
        }
    }

    fun getSwipedForDelete(swipedPosition: Int) : SeriesEntity? = getItem(swipedPosition)

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: SeriesEntity) {
            with(binding) {
                tvSeriesTitle.text = list.name
                tvSeriesRating.text  = list.voteAverage
                tvSeriesLaunchYear.text = list.firstAirDate
                tvSeriesDescription.text = list.overview
                favoriteBtn.visibility = View.INVISIBLE

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${list.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgSeriesPoster)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TITLE, "Favorite Series Detail")
                    intent.putExtra(DetailActivity.ITEM_ID, list.id.toString())
                    intent.putExtra(DetailActivity.CATALOGUE, "Series")
                    intent.putExtra(DetailActivity.IMAGE, "https://image.tmdb.org/t/p/w500${list.posterPath}")
                    it.context.startActivity(intent)
                }
            }
        }

    }



}