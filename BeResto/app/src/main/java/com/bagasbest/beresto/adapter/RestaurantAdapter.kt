package com.bagasbest.beresto.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beresto.R
import com.bagasbest.beresto.RestaurantDetailActivity
import com.bagasbest.beresto.model.RestaurantModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantAdapter(private val restaurantList: List<RestaurantModel>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantAdapter.RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false)
        return RestaurantViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantAdapter.RestaurantViewHolder, position: Int) {
        holder.bind(restaurantList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent (holder.itemView.context, RestaurantDetailActivity::class.java)
            intent.putExtra(RestaurantDetailActivity.EXTRA_RESTAURANT, restaurantList[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: RestaurantModel) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load("https://restaurant-api.dicoding.dev/images/small/${list.pictureId}")
                    .placeholder(R.drawable.ic_baseline_food_bank_24)
                    .error(R.drawable.ic_baseline_food_bank_24)
                    .into(iv_restaurant_image)
                tv_restaurant_title.text = list.name
                tv_restaurant_description.text = list.description
                tv_restaurant_city.text = list.city
                tv_restaurant_rating.text = list.rating.toString()
            }
        }

    }



}