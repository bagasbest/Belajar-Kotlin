package com.bagasbest.fundamental2.myPreLoadData.service

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.myPreLoadData.database.MahasiswaHelper
import com.bagasbest.fundamental2.myPreLoadData.helper.AppPreference
import com.bagasbest.fundamental2.myPreLoadData.model.MahasiswaModel
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class DataManagerService : Service(), CoroutineScope {

    private val TAG = DataManagerService::class.java.simpleName
    private var mActivityMessenger: Messenger? = null

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    companion object {
        const val PREPARATION_MESSAGE = 0
        const val UPDATE_MESSAGE = 1
        const val SUCCESS_MESSAGE = 2
        const val FAILED_MESSAGE = 3
        const val CANCEL_MESSAGE = 4
        const val ACTIVITY_HANDLER = "activity_handler"
        private const val MAX_PROGRESS = 100.0
    }

    override fun onCreate() {
        super.onCreate()
        job = Job()
        Log.d(TAG, "onCreate: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onBind(intent: Intent): IBinder? {
        mActivityMessenger = intent.getParcelableExtra(ACTIVITY_HANDLER)

        // proses ambil data
        loadDataAsync()

        return mActivityMessenger.let { it?.binder }
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnBind: ")
        job.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }

    private fun getData(): Boolean {
        val mahasiswaHelper = MahasiswaHelper.getInstance(applicationContext)
        val appPreference = AppPreference(applicationContext)

        val firstRun = appPreference.firstRun as Boolean
        if(firstRun) {
            val mahasiswaModels = preLoadRaw()
            mahasiswaHelper.open()

            var progress = 30.0
            publishProgress(progress.toInt())
            val progressMaxInsert = 80.0
            val progressDiff = (progressMaxInsert - progress) / mahasiswaModels.size

            var isInsertSuccess: Boolean

            // Gunakan ini untuk insert query dengan menggunakan standar query

            try {
                mahasiswaHelper.beginTransaction()

                loop@ for(model in mahasiswaModels) {
                    when {
                        job.isCancelled -> break@loop
                        else -> {
                            mahasiswaHelper.insertTransaction(model)
                            progress += progressDiff
                            publishProgress(progress.toInt())
                        }
                    }
                }

                when {
                    job.isCancelled -> {
                        isInsertSuccess = false
                        appPreference.firstRun = true
                        sendMessage(CANCEL_MESSAGE)
                    }
                    else -> {
                        mahasiswaHelper.setTransactionSuccess()
                        isInsertSuccess = true
                        appPreference.firstRun = false
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "doInBackGround: Exception")
                isInsertSuccess = false
            } finally {
                mahasiswaHelper.endTransaction()
            }

            // akhir dari standar query
            mahasiswaHelper.close()
            publishProgress(MAX_PROGRESS.toInt())
            return isInsertSuccess

        } else {
            try {
                synchronized(this) {
                    publishProgress(50)
                    publishProgress(MAX_PROGRESS.toInt())
                    return true
                }
            } catch (e: Exception) {
                return false
            }
        }
    }

    private fun preLoadRaw(): ArrayList<MahasiswaModel> {
        val mahasiswModels = ArrayList<MahasiswaModel>()
        var line: String?
        val reader: BufferedReader
        try {
            val rawTxt = resources.openRawResource(R.raw.data_mahasiswa)
            reader = BufferedReader(InputStreamReader(rawTxt))
            do {
                line = reader.readLine()
                val splitstr = line.split("\t".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                val mahasiswaModel = MahasiswaModel()
                mahasiswaModel.name = splitstr[0]
                mahasiswaModel.num = splitstr[1]
                mahasiswModels.add(mahasiswaModel)

            } while (line != null)

        }catch (e: Exception) {
            e.printStackTrace()
        }
        return mahasiswModels
    }

    private fun loadDataAsync () {
        sendMessage(PREPARATION_MESSAGE)
        job = launch {
            val isInsertSuccess = async(Dispatchers.IO) {
                getData()
            }
            if(isInsertSuccess.await()) {
                sendMessage(SUCCESS_MESSAGE)
            } else {
                sendMessage(FAILED_MESSAGE)
            }
        }
        job.start()
    }

    private fun sendMessage (messageStatus: Int) {
        val message = Message.obtain(null, messageStatus)
        try {
            mActivityMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private fun publishProgress(progress: Int) {
        try {
            val message = Message.obtain(null, UPDATE_MESSAGE)
            val bundle = Bundle()
            bundle.putLong("KEY_PROGRESS", progress.toLong())
            message.data = bundle
            mActivityMessenger?.send(message)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }
}