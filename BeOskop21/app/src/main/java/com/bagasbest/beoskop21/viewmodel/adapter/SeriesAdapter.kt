package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ItemSeriesBinding
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.view.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private var listSeries: List<ItemList> = emptyList()

    fun setData (series : List<ItemList>?) {
        if(series == null) return
        this.listSeries = series
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val movie = listSeries[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = this.listSeries.size

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: ItemList) {
            with(binding) {
                tvSeriesTitle.text = series.name
                tvSeriesLaunchYear.text = series.firstAirDate
                tvSeriesDescription.text = series.overview
                tvSeriesRating.text = series.userScore.toString()
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${series.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgSeriesPoster)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TITLE, series.name)
                    intent.putExtra(DetailActivity.ITEM_ID, series.id.toString())
                    intent.putExtra(DetailActivity.CATALOGUE, "Series")
                    intent.putExtra(DetailActivity.IMAGE, "https://image.tmdb.org/t/p/w500${series.posterPath}")
                    it.context.startActivity(intent)
                }

            }
        }

    }
}