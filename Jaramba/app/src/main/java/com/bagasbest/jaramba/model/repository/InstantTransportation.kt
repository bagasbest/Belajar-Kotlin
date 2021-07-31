package com.bagasbest.jaramba.model.repository

import android.content.IntentSender
import android.util.Log
import com.bagasbest.jaramba.view.activity.InstantTransportationActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.ClassCastException

object InstantTransportation {

    private val TAG = InstantTransportation::class.java
    var result:Boolean? = true

    fun showLocationPrompt(context: InstantTransportationActivity) {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val result: Task<LocationSettingsResponse> =
            LocationServices
                .getSettingsClient(context)
                .checkLocationSettings(builder.build())

        result.addOnCompleteListener { task ->
            try {
                val response = task.getResult(ApiException::class.java)
                // All location settings are satisfied. The client can initialize location
                // requests here.
            } catch (e: ApiException) {
                when (e.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                        try {
                            // Cast to a resolvable exception.
                            val resolvable: ResolvableApiException = e as ResolvableApiException
                            // show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult(),
                            resolvable.startResolutionForResult(
                                context, LocationRequest.PRIORITY_HIGH_ACCURACY
                            )

                        } catch (e: IntentSender.SendIntentException) {
                            // ignore the error
                        } catch (e: ClassCastException) {
                            // ignore, should be an imposible error
                        }
                    }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                        // Location settings are not satisfied. But could be fixed by showing the
                        // user a dialog.

                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                    }
                }
            }
        }
    }

    fun saveUserTripToDB(
        transportationMode: String,
        currentLocation: String?,
        destination: String?,
        format: String?,
        totalPerson: Int,
        totalPrice: Int,
        paymentMethod: String?,
        customerUid: String
    ) {
        val timeInMillis = System.currentTimeMillis().toString()


        val data = hashMapOf(
            "tripId" to timeInMillis,
            "customerUid" to customerUid,
            "transportationMode" to transportationMode,
            "currentLocation" to currentLocation,
            "destination" to destination,
            "startDate" to format,
            "finishDate" to "",
            "totalPerson" to totalPerson,
            "totalPrice" to totalPrice,
            "paymentMethod" to paymentMethod,
            "status" to "Dalam Perjalanan",
            "rating" to "",
            "comment" to "",
        )

        FirebaseFirestore
            .getInstance()
            .collection("history")
            .document(timeInMillis)
            .set(data)
            .addOnSuccessListener {
                result = true
                Log.e(TAG.toString(), "TRUE")
            }
            .addOnFailureListener {
                result = false
                Log.e(TAG.toString(), it.toString())
            }

    }


}