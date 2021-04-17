package com.bagasbest.fundamental2.myPreLoadData.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MahasiswaModel(
    var id: Int = 0,
    var name: String? = null,
    var num: String? = null
) : Parcelable