package com.bagasbest.consumerapp.helper

import android.database.Cursor
import com.bagasbest.consumerapp.database.DatabaseContract.UserColumn.Companion.AVATAR
import com.bagasbest.consumerapp.database.DatabaseContract.UserColumn.Companion.URL
import com.bagasbest.consumerapp.database.DatabaseContract.UserColumn.Companion.USERNAME
import com.bagasbest.consumerapp.database.DatabaseContract.UserColumn.Companion._ID
import com.bagasbest.consumerapp.model.FavoriteModel

object MappingHelper {
    fun mapCursorToArrayList(userCursor: Cursor?): ArrayList<FavoriteModel> {
        val favoriteList = ArrayList<FavoriteModel>()

        userCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val username = getString(getColumnIndexOrThrow(USERNAME))
                val imgPhoto = getString(getColumnIndexOrThrow(AVATAR))
                val url = getString(getColumnIndexOrThrow(URL))
                favoriteList.add(FavoriteModel(id = id, username = username, avatar = imgPhoto, url = url))
            }
        }
        return favoriteList
    }
}