package com.bagasbest.jaramba.viewmodel.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.jaramba.model.model.ProfileModel
import com.bagasbest.jaramba.model.repository.ProfileRepository

class ProfileViewModel : ViewModel() {

    private var profileMutableLiveData = ProfileRepository.getProfileMutableLiveData()

    fun setProfile(userUid: String, context: Context) {
        ProfileRepository.setProfile(userUid, context)
    }

    fun getProfileMutableLiveData() : MutableLiveData<ArrayList<ProfileModel>> {
        return profileMutableLiveData
    }

}