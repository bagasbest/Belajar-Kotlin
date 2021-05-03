package com.bagasbest.fundamental2.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import com.bagasbest.fundamental2.academy.data.source.remote.response.ContentResponse
import com.bagasbest.fundamental2.academy.data.source.remote.response.CourseResponse
import com.bagasbest.fundamental2.academy.data.source.remote.response.ModuleResponse
import com.bagasbest.fundamental2.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 200

        @Volatile
        private var instance: RemoteDataSource? = null

        /// Di sini terdapat method getInstance yang berfungsi untuk membuat kelas RemoteDataSource sebagai Singleton. Fungsinya yaitu supaya tercipta satu instance saja di dalam JVM.

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply {
                    instance = this
                }
            }
    }

    fun getAllCourses(callback: LoadCourseCallback)  {
        handler.postDelayed({callback.onAllCourseReceived(jsonHelper.loadCourses())}, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        handler.postDelayed({ callback.onAllModulesReceived(jsonHelper.loadModules(courseId)) }, SERVICE_LATENCY_IN_MILLIS )
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        handler.postDelayed({ callback.onContentReceived(jsonHelper.loadContent(moduleId))}, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadCourseCallback {
        fun onAllCourseReceived(courseResponses: List<CourseResponse>)
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponses: ContentResponse)
    }
}