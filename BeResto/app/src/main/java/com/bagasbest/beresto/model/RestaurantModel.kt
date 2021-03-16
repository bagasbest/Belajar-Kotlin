package com.bagasbest.beresto.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class RestaurantModel(
    var id: String,
    var name: String,
    var description: String,
    var pictureId: String,
    var city: String,
    var rating: Double,
) : Parcelable