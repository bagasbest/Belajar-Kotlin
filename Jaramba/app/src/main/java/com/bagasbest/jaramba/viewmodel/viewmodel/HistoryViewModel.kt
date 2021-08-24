package com.bagasbest.jaramba.viewmodel.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.model.HistoryModel
import com.bagasbest.jaramba.model.repository.HistoryRepository

class HistoryViewModel : ViewModel() {

    private val TAG = HistoryViewModel::class.java.simpleName


    private var historyMutableLiveData = HistoryRepository.getHistoryUserMutableLiveData()

    fun setHistory(customerUid: String, context: Context) {
        HistoryRepository.setHistory(customerUid, context)
    }

    fun setHistoryByDate(customerUid: String, context: Context, date: String) {
        HistoryRepository.setHistoryByDate(customerUid, context, date)
    }

    fun getHistoryMutableLiveData() : MutableLiveData<ArrayList<HistoryModel>> {
        Log.e(TAG, "Saved the History Live Data")
        Log.e(TAG, historyMutableLiveData.toString())
        return historyMutableLiveData
    }

}