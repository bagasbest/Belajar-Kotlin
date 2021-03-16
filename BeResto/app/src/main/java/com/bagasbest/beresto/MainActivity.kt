package com.bagasbest.beresto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bagasbest.beresto.adapter.RestaurantAdapter
import com.bagasbest.beresto.model.RestaurantModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private val list = ArrayList<RestaurantModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_BeResto)
        setContentView(R.layout.activity_main)

        rv_restaurant_list.setHasFixedSize(true)
        list.addAll(extractRestaurants())



    }

    private fun extractRestaurants () : ArrayList<RestaurantModel> {
        progress_bar.visibility = View.VISIBLE
        val url = "https://restaurant-api.dicoding.dev/list"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                /// saya tidak tahu kenapa lenght dari response hanya 4, padahal ada 20 total nya
                Log.d("tag", response.length().toString())
                val countObjectOnRestaurant = response.getInt("count")
                val jsonArray = response.getJSONArray("restaurants")
                for (i in 0 until countObjectOnRestaurant) {
                    try {
                        val restaurantObject = jsonArray.getJSONObject(i)
                        val id = restaurantObject.getString("id")
                        val name = restaurantObject.getString("name")
                        val description = restaurantObject.getString("description")
                        val pictureId = restaurantObject.getString("pictureId")
                        val city = restaurantObject.getString("city")
                        val rating = restaurantObject.getDouble("rating")

                        val restaurant = RestaurantModel(
                            id,
                            name,
                            description,
                            pictureId,
                            city,
                            rating
                        )
                        list.add(restaurant)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        progress_bar.visibility = View.INVISIBLE
                    }
                }

                rv_restaurant_list.layoutManager = LinearLayoutManager(this)
                val adapter = RestaurantAdapter(list)
                rv_restaurant_list.adapter = adapter
                progress_bar.visibility = View.INVISIBLE

            }
        )
        { error -> Log.d("tag", "onErrorResponse: " + error.message)  }

        queue.add(jsonObjectRequest)
        return list
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.settings) {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}