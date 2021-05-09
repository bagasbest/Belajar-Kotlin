package com.bagasbest.jaramba.model

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bagasbest.jaramba.view.activity.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AppRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()



    fun register(email: String, password: String, context: RegisterActivity) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    Toast.makeText(context, "Registrasi gagal: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser>  {
        return userMutableLiveData
    }

}