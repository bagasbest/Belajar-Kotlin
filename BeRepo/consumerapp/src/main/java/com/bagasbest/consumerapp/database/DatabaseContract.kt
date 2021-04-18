package com.bagasbest.consumerapp.database

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.bagasbest.berepo"
    const val SCHEME = "content"

    class UserColumn : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_user"
            const val _ID = "_id"
            const val USERNAME = "username"
            const val AVATAR = "avatar"
            const val URL = "url"

            // create content : //com.bagasbest.berepo/user
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}