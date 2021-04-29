package com.bagasbest.fundamental2.academy.data.source.remote

import com.bagasbest.fundamental2.academy.data.source.remote.response.ContentResponse
import com.bagasbest.fundamental2.academy.data.source.remote.response.CourseResponse
import com.bagasbest.fundamental2.academy.data.source.remote.response.ModuleResponse
import com.bagasbest.fundamental2.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
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

    fun getAllCourses() : List<CourseResponse> = jsonHelper.loadCourses()

    fun getModules(courseId: String) : List<ModuleResponse> = jsonHelper.loadModules(courseId)

    fun getContent(moduleId: String) : ContentResponse = jsonHelper.loadContent(moduleId)
}