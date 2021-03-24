package com.bagasbest.fundamental2.myBroadcastReceiver


import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService


class DownloadService : JobIntentService() {

    companion object {
        val TAG: String = DownloadService::class.java.simpleName
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if(intent != null) {
            enqueueWork(this, this::class.java, 101, intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "Download Sevice Dijalankan")
        try {
            Thread.sleep(5000)
        }catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val notifyFinishIntent = Intent(MyBroadcastReceiverMainActivity.ACTION_DOWNLOAD_STATUS)
        sendBroadcast(notifyFinishIntent)
    }




}