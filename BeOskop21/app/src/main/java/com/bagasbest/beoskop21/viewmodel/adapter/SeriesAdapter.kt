package com.bagasbest.beoskop21.viewmodel.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.ItemSeriesBinding
import com.bagasbest.beoskop21.model.model.SeriesModel
import com.bagasbest.beoskop21.view.activity.DetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class SeriesAdapter : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    private var listSeries = ArrayList<SeriesModel>()

    fun setData (series : List<SeriesModel>?) {
        if(series == null) return
        this.listSeries.clear()
        this.listSeries.addAll(series)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(listSeries[position])
    }

    override fun getItemCount(): Int = this.listSeries.size

    inner class SeriesViewHolder(private val binding: ItemSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesModel) {
            with(binding) {
                tvSeriesTitle.text = series.title
                tvSeriesLaunchYear.text = series.year.toString()
                tvSeriesDuration.text = series.durationEpisode
                tvSeriesDescription.text = series.description
                tvSeriesRating.text = series.userScore.toString()
                Glide.with(itemView.context)
                    .load(series.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgSeriesPoster)

                itemView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ITEMS, series.title)
                    intent.putExtra(DetailActivity.TITLE, "Series")
                    it.context.startActivity(intent)
                }

            }
        }

    }
}