package com.bagasbest.jaramba.model.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryModel(
    var comment: String? = null,
    var currentLocation: String? = null,
    var customerUid: String? = null,
    var destination: String? = null,
    var finishDate: String? = null,
    var paymentMethod: String? = null,
    var rating: String? = null,
    var startDate: String? = null,
    var status: String? = null,
    var totalPerson: Int? = 0,
    var totalPrice: Int? = 0,
    var transportationMode: String? = null,
    var tripId: String? = null,
) : Parcelable