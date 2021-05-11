package com.bagasbest.jaramba.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.ForgotPasswordRepository
import com.bagasbest.jaramba.view.activity.ForgotPasswordActivity
import com.google.firebase.auth.FirebaseUser

class ForgotPasswordViewModel: ViewModel() {
    private var userMutableLiveData = ForgotPasswordRepository.getUserMutableLiveData()

    fun forgotPassword(email: String, context: ForgotPasswordActivity) {
        ForgotPasswordRepository.forgotPassword(email, context)
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }

}