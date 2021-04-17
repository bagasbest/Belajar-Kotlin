package com.bagasbest.fundamental2.myPreLoadData

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bagasbest.fundamental2.databinding.ActivityMyPreloadDataMainBinding
import com.bagasbest.fundamental2.myPreLoadData.service.DataManagerService
import com.bagasbest.fundamental2.myPreLoadData.service.DataManagerService.Companion.CANCEL_MESSAGE
import com.bagasbest.fundamental2.myPreLoadData.service.DataManagerService.Companion.FAILED_MESSAGE
import com.bagasbest.fundamental2.myPreLoadData.service.DataManagerService.Companion.PREPARATION_MESSAGE
import com.bagasbest.fundamental2.myPreLoadData.service.DataManagerService.Companion.SUCCESS_MESSAGE
import com.bagasbest.fundamental2.myPreLoadData.service.DataManagerService.Companion.UPDATE_MESSAGE
import java.lang.ref.WeakReference

class MyPreloadDataMainActivity : AppCompatActivity(), HandlerCallback {

    private lateinit var binding : ActivityMyPreloadDataMainBinding
    private  lateinit var mBoundService: Messenger
    private var mServiceBound: Boolean = false

    /*
    Service Connection adalah interface yang digunakan untuk menghubungkan antara boundservice dengan activity
    */
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            mBoundService = Messenger(service)
            mServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPreloadDataMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mBoundServiceIntent = Intent(this, DataManagerService::class.java)
        val mActivityMessenger = Messenger(IncomingHandler(this))
        mBoundServiceIntent.putExtra(DataManagerService.ACTIVITY_HANDLER, mActivityMessenger)

        bindService(mBoundServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        super.onDestroy()

        unbindService(mServiceConnection)
    }



    override fun onPreparation() {
        Toast.makeText(this, "MEMULAI MEMUAT DATA", Toast.LENGTH_LONG).show()
    }

    override fun updateProgress(progress: Long) {
        Log.d("PROGRESS", "updateProgress: $progress")
        binding.progressBar.progress = progress.toInt()
    }

    override fun loadSuccess() {
        Toast.makeText(this, "BERHASIL", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MahasiswaActivity::class.java))
        finish()
    }

    override fun loadFailed() {
        Toast.makeText(this, "GAGAL", Toast.LENGTH_LONG).show()
    }

    override fun loadCancel() {
        finish()
    }

    private class IncomingHandler(callback: HandlerCallback) : Handler(Looper.getMainLooper()) {

        private var weakCallback: WeakReference<HandlerCallback> = WeakReference(callback)

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                PREPARATION_MESSAGE -> weakCallback.get()?.onPreparation()
                UPDATE_MESSAGE -> {
                    val bundle = msg.data
                    val progress = bundle.getLong("KEY_PROGRESS")
                    weakCallback.get()?.updateProgress(progress)
                }
                SUCCESS_MESSAGE -> weakCallback.get()?.loadSuccess()
                FAILED_MESSAGE -> weakCallback.get()?.loadFailed()
                CANCEL_MESSAGE -> weakCallback.get()?.loadCancel()
            }
        }
    }


}

private interface HandlerCallback {
    fun onPreparation()

    fun updateProgress(progress: Long)

    fun loadSuccess()

    fun loadFailed()

    fun loadCancel()
}