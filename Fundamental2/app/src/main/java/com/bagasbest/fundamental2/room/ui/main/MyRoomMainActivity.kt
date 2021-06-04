package com.bagasbest.fundamental2.room.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.ActivityMyRoomMainBinding
import com.bagasbest.fundamental2.room.database.Note
import com.bagasbest.fundamental2.room.helper.SortUtils
import com.bagasbest.fundamental2.room.ui.insert.adapter.NoteAdapter
import com.bagasbest.fundamental2.room.ui.insert.adapter.NotePagedListAdapter
import com.bagasbest.fundamental2.room.ui.insert.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MyRoomMainActivity : AppCompatActivity() {

    private var binding: ActivityMyRoomMainBinding? = null


    private lateinit var adapter: NotePagedListAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRoomMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.title = "ROOM"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mainViewModel = obtainViewModel(this)
        mainViewModel.getAllNotes(SortUtils.NEWEST).observe(this, noteObserver)

        adapter = NotePagedListAdapter(this)

        binding?.rvNotes?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotes?.setHasFixedSize(true)
        binding?.rvNotes?.adapter = adapter

        binding?.fabAdd?.setOnClickListener {
            if(it.id == R.id.fab_add) {
                val intent = Intent(this, MyRoomNoteAddUpdateActivity::class.java)
                startActivityForResult(intent, MyRoomNoteAddUpdateActivity.REQUEST_ADD)
            }
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    private val noteObserver = Observer<PagedList<Note>> { noteList ->
        if (noteList != null) {
            adapter.submitList(noteList)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null) {
            if(requestCode == MyRoomNoteAddUpdateActivity.REQUEST_ADD) {
                if(resultCode == MyRoomNoteAddUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added))
                }
            } else if (requestCode == MyRoomNoteAddUpdateActivity.REQUEST_UPDATE){
                if (resultCode == MyRoomNoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed))
                } else if (resultCode == MyRoomNoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted))
                }
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding?.root as View, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.room_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_oldest -> sort = SortUtils.OLDEST
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        mainViewModel.getAllNotes(sort).observe(this, noteObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }
}