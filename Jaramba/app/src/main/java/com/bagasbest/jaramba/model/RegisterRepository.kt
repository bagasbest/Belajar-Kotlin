package com.bagasbest.jaramba.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bagasbest.jaramba.view.activity.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object RegisterRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private var userMutableLiveData = MutableLiveData<FirebaseUser>()
    private val TAG = RegisterRepository::class.java.simpleName


    fun register(email: String, password: String, username: String, phone: String, context: RegisterActivity) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    saveUserRegisteredData(email,  username, phone, context)
                } else {
                    Log.d(TAG, task.exception.toString())
                    Toast.makeText(context, "Registrasi gagal: " + task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUserRegisteredData(
        email: String,
        username: String,
        phone: String,
        context: RegisterActivity
    ) {
        // create new user with their data
        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "phone" to phone,
            "image" to "",
        )

        // add a new document with generated ID
        firebaseAuth.currentUser?.uid?.let {
            Firebase.firestore.collection("users")
                .document(it)
                .set(user)
                .addOnSuccessListener {
                    // send user email verification before login
                    sendEmailVerification()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        "Terjadi masalah ketika proses pendaftaran: $it",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.w(TAG, "Terjadi masalah ketika proses pendaftaran: ", it)
                }
        }
    }

    private fun sendEmailVerification() {
        firebaseAuth.currentUser?.sendEmailVerification()
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d(TAG, "Sukses mendaftar")
                    userMutableLiveData.postValue(firebaseAuth.currentUser)
                } else {
                    Log.d(TAG, "Email tidak terkirim: ${it.exception?.message}")
                }
            }
    }

    fun getUserMutableLiveData() : MutableLiveData<FirebaseUser>  {
        return userMutableLiveData
    }

}