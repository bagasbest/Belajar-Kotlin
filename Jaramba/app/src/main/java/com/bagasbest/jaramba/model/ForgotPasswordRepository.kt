package com.bagasbest.jaramba.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bagasbest.jaramba.view.activity.ForgotPasswordActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object ForgotPasswordRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private val TAG = ForgotPasswordRepository::class.java.simpleName

    fun forgotPassword(email: String, context: ForgotPasswordActivity) {

        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Log.d(TAG, "Konfirmasi berhasil: ")
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    Log.d(TAG, it.exception.toString())
                    Toast.makeText(
                        context,
                        "Konfirmasi gagal: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser> {
        return userMutableLiveData
    }
}