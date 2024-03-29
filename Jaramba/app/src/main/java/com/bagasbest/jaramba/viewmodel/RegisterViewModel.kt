package com.bagasbest.jaramba.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.RegisterRepository
import com.bagasbest.jaramba.view.activity.RegisterActivity
import com.google.firebase.auth.FirebaseUser


class RegisterViewModel : ViewModel() {
    private var userMutableLiveData = RegisterRepository.getUserMutableLiveData()


    fun register(
        email: String,
        password: String,
        username: String,
        phone: String,
        context: RegisterActivity
    ) {
        RegisterRepository.register(email, password, username, phone, context)
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser>  {
        return userMutableLiveData
    }




}