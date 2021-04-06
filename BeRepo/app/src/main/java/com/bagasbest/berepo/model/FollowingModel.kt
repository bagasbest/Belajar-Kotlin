package com.bagasbest.berepo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FollowingModel(
    var username: String? = null,
    var id: Int? = 0,
    var avatar: String? = null,
//    var fullname: String? = null,
//    var location: String? = null,
//    var email: String? = null,
//    var bio: String? = null,
//    var blog: String? = null,
//    var repository: Int? = 0,
    var url: String? = null,
) : Parcelable