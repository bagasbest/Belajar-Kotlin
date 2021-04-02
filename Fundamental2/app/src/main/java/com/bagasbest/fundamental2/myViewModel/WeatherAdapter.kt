package com.bagasbest.fundamental2.myViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.WeatherItemBinding

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val mData = ArrayList<WeatherModel>()

    fun setData(items: ArrayList<WeatherModel>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size


    inner class WeatherViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        private val binding = WeatherItemBinding.bind(itemView)
        fun bind(weatherModel: WeatherModel) {
            with(itemView) {
                binding.textCity.text = weatherModel.name
                binding.textTemp.text = weatherModel.temperature
                binding.textDesc.text = weatherModel.description
            }
        }

    }
}