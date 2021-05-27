package com.bagasbest.fundamental2.room.ui.insert.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.bagasbest.fundamental2.room.database.Note
import com.bagasbest.fundamental2.room.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {

    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update (note: Note) {
        mNoteRepository.update(note)
    }

    fun delete (note: Note) {
        mNoteRepository.delete(note)
    }


}