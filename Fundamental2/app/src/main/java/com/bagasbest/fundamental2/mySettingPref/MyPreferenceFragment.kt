package com.bagasbest.fundamental2.mySettingPref

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import com.bagasbest.fundamental2.R
import com.bumptech.glide.Glide.init

class MyPreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {


    private lateinit var NAME: String
    private lateinit var EMAIL: String
    private lateinit var AGE: String
    private lateinit var PHONE: String
    private lateinit var LOVE: String

    private lateinit var namePrefenrce : EditTextPreference
    private lateinit var emailPreference : EditTextPreference
    private lateinit var agePreference: EditTextPreference
    private lateinit var phonePrefence: EditTextPreference
    private lateinit var isLoveMuPreference: CheckBoxPreference

    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        namePrefenrce.summary = sh.getString(NAME, DEFAULT_VALUE)
        emailPreference.summary = sh.getString(EMAIL, DEFAULT_VALUE)
        agePreference.summary = sh.getString(AGE, DEFAULT_VALUE)
        phonePrefence.summary = sh.getString(PHONE, DEFAULT_VALUE)
        isLoveMuPreference.isChecked = sh.getBoolean(LOVE, false)
    }

    private fun init () {
        NAME = resources.getString(R.string.key_name)
        EMAIL = resources.getString(R.string.key_email)
        AGE = resources.getString(R.string.key_age)
        PHONE = resources.getString(R.string.key_phone)
        LOVE = resources.getString(R.string.key_love)


        namePrefenrce = findPreference<EditTextPreference>(NAME) as EditTextPreference
        emailPreference = findPreference<EditTextPreference>(EMAIL) as EditTextPreference
        agePreference = findPreference<EditTextPreference>(AGE) as EditTextPreference
        phonePrefence = findPreference<EditTextPreference>(PHONE) as EditTextPreference
        isLoveMuPreference = findPreference<CheckBoxPreference>(LOVE) as CheckBoxPreference

    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if(key == NAME) {
            namePrefenrce.summary = sharedPreferences.getString(NAME, DEFAULT_VALUE)
        }

        if (key == EMAIL) {
            emailPreference.summary = sharedPreferences.getString(EMAIL, DEFAULT_VALUE)
        }
        if (key == AGE) {
            agePreference.summary = sharedPreferences.getString(AGE, DEFAULT_VALUE)
        }
        if (key == PHONE) {
            phonePrefence.summary = sharedPreferences.getString(PHONE, DEFAULT_VALUE)
        }
        if (key == LOVE) {
            isLoveMuPreference.isChecked = sharedPreferences.getBoolean(LOVE, false)
        }
    }

}