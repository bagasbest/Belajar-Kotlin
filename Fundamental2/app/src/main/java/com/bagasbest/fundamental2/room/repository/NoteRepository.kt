package com.bagasbest.fundamental2.room.repository

import android.app.Application
import androidx.paging.DataSource
import com.bagasbest.fundamental2.room.database.Note
import com.bagasbest.fundamental2.room.database.NoteDao
import com.bagasbest.fundamental2.room.database.NoteRoomDatabase
import com.bagasbest.fundamental2.room.helper.SortUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()

    }

    fun getAllNotes(sort: String): DataSource.Factory<Int, Note> {
        val query = SortUtils.getSortedQuery(sort)
        return mNotesDao.getAllNotes(query)
    }

    fun insert(note: Note) {
        executorService.execute {mNotesDao.insert(note)}
    }

    fun update(note:Note) {
        executorService.execute {mNotesDao.update(note)}
    }

    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }
}