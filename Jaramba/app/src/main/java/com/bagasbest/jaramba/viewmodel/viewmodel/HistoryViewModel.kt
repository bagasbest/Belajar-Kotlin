package com.bagasbest.jaramba.viewmodel.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.model.HistoryModel
import com.bagasbest.jaramba.model.repository.HistoryRepository

class HistoryViewModel : ViewModel() {

    private var historyMutableLiveData = HistoryRepository.getHistoryUserMutableLiveData()

    fun setHistory(customerUid: String, context: Context) {
        HistoryRepository.setHistory(customerUid, context)
    }

    fun setHistoryByDate(customerUid: String, context: Context, date: String) {
        HistoryRepository.setHistoryByDate(customerUid, context, date)
    }

    fun getHistoryMutableLiveData() : MutableLiveData<ArrayList<HistoryModel>> {
        return historyMutableLiveData
    }

}