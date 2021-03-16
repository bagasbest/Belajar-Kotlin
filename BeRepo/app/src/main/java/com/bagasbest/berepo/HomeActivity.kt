package com.bagasbest.berepo

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.berepo.adapter.UserAdapter
import com.bagasbest.berepo.model.UserModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val list = ArrayList<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        title = "Daftar Pengguna Github"


        rv_data.setHasFixedSize(true)
        list.addAll(getListUserGithub())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListUserGithub() : ArrayList<UserModel> {
        val fullname = resources.getStringArray(R.array.name)
        val username = resources.getStringArray(R.array.username)
        val company = resources.getStringArray(R.array.company)
        val location = resources.getStringArray(R.array.location)
        val follower = resources.getStringArray(R.array.followers)
        val following = resources.getStringArray(R.array.following)
        val repository = resources.getStringArray(R.array.repository)
        val avatar = resources.obtainTypedArray(R.array.avatar)

        for(position in username.indices) {
            val user = UserModel(
                '@' + username[position],
                fullname[position],
                avatar.getResourceId(position, -1),
                company[position],
                location[position],
                repository[position],
                follower[position],
                following[position],
            )
            list.add(user)
        }
        return list

    }

    private fun showRecyclerList () {
        rv_data.layoutManager = LinearLayoutManager(this)
        val listGithubUserAdapter = UserAdapter(list)
        rv_data.adapter = listGithubUserAdapter
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
                Toast.makeText(this@HomeActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
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

}