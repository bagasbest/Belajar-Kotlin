package com.bagasbest.jaramba.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bagasbest.jaramba.model.model.HistoryModel
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

object HistoryRepository {
    private var historyMutableLiveData = MutableLiveData<ArrayList<HistoryModel>>()
    private val listHistory = ArrayList<HistoryModel>()
    private val TAG = HistoryRepository::class.java.simpleName

    fun setHistory(customerUid: String, context: Context) {

        try {
            listHistory.clear()

            FirebaseFirestore
                .getInstance()
                .collection("history")
                .whereEqualTo("customerUid", customerUid)
                .get()
                .addOnSuccessListener { documents ->
                    for(document in documents) {
                        val historyModel = HistoryModel()
                        historyModel.comment = document.data["comment"].toString()
                        historyModel.currentLocation = document.data["currentLocation"].toString()
                        historyModel.customerUid = document.data["customerUid"].toString()
                        historyModel.destination = document.data["destination"].toString()
                        historyModel.finishDate = document.data["finishDate"].toString()
                        historyModel.paymentMethod = document.data["paymentMethod"].toString()
                        historyModel.rating = document.data["rating"].toString()
                        historyModel.startDate = document.data["startDate"].toString()
                        historyModel.status = document.data["status"].toString()
                        historyModel.totalPerson = document.data["totalPerson"].toString().toInt()
                        historyModel.totalPrice = document.data["totalPrice"].toString().toInt()
                        historyModel.transportationMode = document.data["transportationMode"].toString()
                        historyModel.tripId = document.data["tripId"].toString()

                        listHistory.add(historyModel)

                    }
                    historyMutableLiveData.postValue(listHistory)
                }
                .addOnFailureListener {
                    Log.w(TAG, "Error getting documents: ", it)
                    Toast.makeText(context, "error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    fun setHistoryByDate(customerUid: String, context: Context, date: String) {

        try {
            listHistory.clear()

            FirebaseFirestore
                .getInstance()
                .collection("history")
                .whereEqualTo("customerUid", customerUid)
                .whereEqualTo("startDate", date)
                .get()
                .addOnSuccessListener { documents ->
                    for(document in documents) {
                        val historyModel = HistoryModel()
                        historyModel.comment = document.data["comment"].toString()
                        historyModel.currentLocation = document.data["currentLocation"].toString()
                        historyModel.customerUid = document.data["customerUid"].toString()
                        historyModel.destination = document.data["destination"].toString()
                        historyModel.finishDate = document.data["finishDate"].toString()
                        historyModel.paymentMethod = document.data["paymentMethod"].toString()
                        historyModel.rating = document.data["rating"].toString()
                        historyModel.startDate = document.data["startDate"].toString()
                        historyModel.status = document.data["status"].toString()
                        historyModel.totalPerson = document.data["totalPerson"].toString().toInt()
                        historyModel.totalPrice = document.data["totalPrice"].toString().toInt()
                        historyModel.transportationMode = document.data["transportationMode"].toString()
                        historyModel.tripId = document.data["tripId"].toString()

                        listHistory.add(historyModel)

                    }
                    historyMutableLiveData.postValue(listHistory)
                }
                .addOnFailureListener {
                    Log.w(TAG, "Error getting documents: ", it)
                    Toast.makeText(context, "error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    fun getHistoryUserMutableLiveData() : MutableLiveData<ArrayList<HistoryModel>> {
        return historyMutableLiveData
    }
}