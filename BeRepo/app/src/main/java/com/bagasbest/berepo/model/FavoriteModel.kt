package com.bagasbest.berepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteModel(
    var username: String? = null,
    var id: Int? = 0,
    var avatar: String? = null,
    var url: String? = null,
) : Parcelable