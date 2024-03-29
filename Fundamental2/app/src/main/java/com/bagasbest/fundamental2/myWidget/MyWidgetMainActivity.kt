package com.bagasbest.fundamental2.myWidget

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bagasbest.fundamental2.databinding.ActivityMyWidgetMainBinding

class MyWidgetMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyWidgetMainBinding

    companion object {
        private const val JOB_ID = 100
        private const val SCHEDULE_OF_PERIOD = 86000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyWidgetMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{ startJob() }
        binding.btnStop.setOnClickListener { cancelJob() }


    }

    private fun startJob () {
        val mServiceComponent = ComponentName(this, UpdateWidgetService::class.java)

        val builder = JobInfo.Builder(JOB_ID, mServiceComponent)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setPeriodic(900000) // 15 menit
        } else {
            builder.setPeriodic(SCHEDULE_OF_PERIOD) // 3 MENIT
        }
        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(builder.build())

        Toast.makeText(this, "Job Service started", Toast.LENGTH_SHORT).show()

    }

    private fun cancelJob () {
        val tm = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        tm.cancel(JOB_ID)
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show()
    }
}