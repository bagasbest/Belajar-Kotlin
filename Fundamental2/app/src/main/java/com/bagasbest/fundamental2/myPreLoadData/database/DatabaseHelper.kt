package com.bagasbest.fundamental2.myPreLoadData.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bagasbest.fundamental2.myNotesApp.db.DatabaseContract.NoteColumn.Companion._ID
import com.bagasbest.fundamental2.myPreLoadData.database.DatabaseContract.MahasiswaColumns.Companion.NAMA
import com.bagasbest.fundamental2.myPreLoadData.database.DatabaseContract.MahasiswaColumns.Companion.NIM
import com.bagasbest.fundamental2.myPreLoadData.database.DatabaseContract.TABLE_NAME


internal class DatabaseHelper(context: Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dbmahasiswa"
        private const val DATABASE_VERSION = 1
        private val CREATE_TABLE_MAHASISWA = "CREATE TABLE $TABLE_NAME ($_ID INTEGER PRIMARY KEY AUTOINCREMENT, $NAMA TEXT NOT NULL, $NIM TEXT NOT NULL);"
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(CREATE_TABLE_MAHASISWA)
    }

    override fun onUpgrade(p0: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        p0.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(p0)
    }

}