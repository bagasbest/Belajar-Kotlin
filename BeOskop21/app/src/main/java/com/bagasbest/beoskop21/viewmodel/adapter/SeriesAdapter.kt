package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
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

class SeriesAdapter : PagedListAdapter<SeriesEntity, SeriesAdapter.SeriesViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    companion object {
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
        val series = getItem(position)
        if(series != null) {
            holder.bind(series)
        }
    }


    inner class SeriesViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {
                tvSeriesTitle.text = series.name
                tvSeriesLaunchYear.text = series.firstAirDate
                tvSeriesDescription.text = series.overview
                tvSeriesRating.text = series.voteAverage.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${series.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgSeriesPoster)

                if(series.isFavorite) {
                    favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                }

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TITLE, "Series Detail")
                    intent.putExtra(DetailActivity.TITLE, series.name)
                    intent.putExtra(DetailActivity.ITEM_ID, series.id.toString())
                    intent.putExtra(DetailActivity.CATALOGUE, "Series")
                    intent.putExtra(DetailActivity.IMAGE, "https://image.tmdb.org/t/p/w500${series.posterPath}")
                    it.context.startActivity(intent)
                }

                favoriteBtn.setOnClickListener {
                    if(series.isFavorite) {
                        favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    } else {
                        favoriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                    onItemClickCallBack.clickFavoriteButton(series)
                }

            }
        }
    }

    interface OnItemClickCallBack {
        fun clickFavoriteButton(series: SeriesEntity)
    }
}