package com.bagasbest.jaramba.viewmodel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.repository.LoginRepository
import com.bagasbest.jaramba.view.activity.LoginActivity
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {
    private val userMutableLiveData = LoginRepository.getUserMutableLiveData()


    fun login(email: String, password: String, context: LoginActivity) {
        LoginRepository.login(email, password, context)
    }

    fun getUserMutableLiveData(): MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }
}