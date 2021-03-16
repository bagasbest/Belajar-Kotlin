package com.bagasbest.beresto

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bagasbest.beresto.model.RestaurantModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_restaurant_detail.*


class RestaurantDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RESTAURANT = "extra_restaurant"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BeResto)
        setContentView(R.layout.activity_restaurant_detail)

        val restaurant =
            intent.getParcelableExtra<RestaurantModel>(EXTRA_RESTAURANT) as RestaurantModel
        val name = restaurant.name
        val description = restaurant.description
        val pictureId = restaurant.pictureId
        val city = restaurant.city
        val rating = restaurant.rating

        tv_name_restaurant_detail.text = name
        tv_restaurant_description_detail.text = description
        tv_restaurant_city_detail.text = city
        tv_restaurant_rating_detail.text = rating.toString()
        Glide.with(this).load("https://restaurant-api.dicoding.dev/images/small/${pictureId}").placeholder(R.drawable.ic_baseline_location_city_24)
            .error(R.drawable.ic_baseline_location_city_24).into(iv_image_restaurant_detail)

        favorite.setOnClickListener {
            Toast.makeText(this, "I like $name", Toast.LENGTH_SHORT).show()
        }

        share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey please check $name, this is recommended place to eat")
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

    }
}