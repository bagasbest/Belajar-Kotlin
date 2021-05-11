package com.bagasbest.jaramba.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bagasbest.jaramba.view.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object LoginRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private val TAG = LoginRepository::class.java.simpleName

    fun login(email: String, password: String, context: LoginActivity) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {

                    // check if user has verified account or not
                    if(firebaseAuth.currentUser.isEmailVerified) {
                        Log.d(TAG, "signInWithEmail:success")
                        userMutableLiveData.postValue(firebaseAuth.currentUser)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            context, "Email belum anda verifikasi",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        context, "Authentication failed: " + it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser>  {
        return LoginRepository.userMutableLiveData
    }


}