package com.bagasbest.berepo

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.berepo.adapter.UserAdapter
import com.bagasbest.berepo.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter : UserAdapter
    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        title = "Daftar Pengguna Github"

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        rv_data.layoutManager = LinearLayoutManager(this)
        rv_data.adapter = adapter

        homeViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(HomeViewModel::class.java)

        homeViewModel.getUser().observe(this, {userItems ->
            if(userItems != null) {
                adapter.setData(userItems)
                showLoading(false)
            }
        })

        homeViewModel.getUserCount().observe(this, {userCount ->
            if(userCount != null ) {
                val len = userCount.toString().length
                item_count.text = userCount.toString().substring(25, len-2) + " Pengguna terdaftar"
                showLoading(false)
            } else {
                item_count.text = "0 Pengguna terdaftar"
                showLoading(false)
            }
        })

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            @SuppressLint("SetTextI18n")
            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("LOG", newText)
                if(newText.isEmpty()) {
                    rv_data.visibility = View.INVISIBLE
                    item_count.text = "0 Pengguna terdaftar"
                    empty_image.visibility = View.VISIBLE
                    empty_title.visibility = View.VISIBLE

                } else {
                    rv_data.visibility = View.VISIBLE
                    empty_image.visibility = View.INVISIBLE
                    empty_title.visibility = View.INVISIBLE
                    showLoading(true)

                    homeViewModel.setUser(newText)
                }
                return true
            }
        })
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.settings) {
            startActivity(Intent(this, AboutMeActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            homeProgressBar.visibility = View.VISIBLE
        } else {
            homeProgressBar.visibility = View.INVISIBLE
        }
    }

}