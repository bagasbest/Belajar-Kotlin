package com.bagasbest.fundamental2.myNotesApp.db

import android.provider.BaseColumns

internal class DatabaseContract  {

    internal class NoteColumn : BaseColumns {
        companion object {
            const val TABLE_NAME = "note"
            const val _ID = "_id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val DATE = "date"
        }
    }

}