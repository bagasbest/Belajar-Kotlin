package com.bagasbest.fundamental2.academy.data

import androidx.room.ColumnInfo

data class ContentEntity(
    @ColumnInfo(name = "content")
    var content: String,
)