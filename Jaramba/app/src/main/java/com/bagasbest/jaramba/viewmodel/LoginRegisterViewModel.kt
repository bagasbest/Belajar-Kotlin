package com.bagasbest.jaramba.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.AppRepository
import com.bagasbest.jaramba.view.activity.RegisterActivity
import com.google.firebase.auth.FirebaseUser


class LoginRegisterViewModel : ViewModel() {
    private var userMutableLiveData = AppRepository.getUserMutableLiveData()


    fun register(email: String, password: String, context: RegisterActivity) {
        AppRepository.register(email, password, context)
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser>  {
        return userMutableLiveData
    }




}