package com.bagasbest.berepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var username: String? = null,
    var id: Int? = 0,
    var avatar: String? = null,
) : Parcelable