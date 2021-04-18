package com.bagasbest.berepo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bagasbest.berepo.service.AlarmReceiver
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : AppCompatActivity() {

    companion object {
        const val SHARED_PREFS = "shared_prefs"
        const val SWITCH = "switch"
    }

    private var remembered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        supportActionBar?.title = resources.getString(R.string.reminder_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val alarmReceiver = AlarmReceiver()

        sw_reminder.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                alarmReceiver.setRepeatingAlarm(this)
                val checked = sw_reminder.isChecked
                showSnackBar(resources.getString(R.string.daily_reminder_on))
                saveData(checked)

            } else {
                alarmReceiver.cancelAlarm(this)
                val checked = sw_reminder.isChecked
                showSnackBar(resources.getString(R.string.daily_reminder_off))
                saveData(checked)
            }
        }


        checkIfSwitchOn()
        updateViews()


    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(sw_reminder, msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun saveData(checked: Boolean) {
        val sharedPreferences =
            getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putBoolean(SWITCH, checked)
        editor.apply()
    }

    private fun updateViews() {
        sw_reminder.isChecked = remembered
    }

    private fun checkIfSwitchOn() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        remembered = sharedPreferences.getBoolean(SWITCH, false)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}