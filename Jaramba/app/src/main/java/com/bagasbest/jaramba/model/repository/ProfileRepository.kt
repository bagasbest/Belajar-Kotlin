package com.bagasbest.jaramba.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bagasbest.jaramba.model.model.ProfileModel
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

object ProfileRepository {

    private var profileMutableLiveData = MutableLiveData<ArrayList<ProfileModel>>()
    private val listProfile = ArrayList<ProfileModel>()
    private val TAG = ProfileRepository::class.java.simpleName

    fun setProfile(uid: String, context: Context) {

        try {
            listProfile.clear()

            FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { documents ->

                    val model = ProfileModel()
                    model.username = documents.data?.get("username").toString()
                    model.email = documents.data?.get("email").toString()
                    model.phone = documents.data?.get("phone").toString()
                    model.image = documents.data?.get("image").toString()

                    listProfile.add(model)
                    profileMutableLiveData.postValue(listProfile)

                }
                .addOnFailureListener {
                    Log.w(TAG, "Error getting documents: ", it)
                    Toast.makeText(context, "error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        } catch (error: Exception) {
            error.printStackTrace()
        }
    }

    fun getProfileMutableLiveData() : MutableLiveData<ArrayList<ProfileModel>> {
        return profileMutableLiveData
    }



}