package com.bagasbest.fundamental2.academy.data.source

import androidx.lifecycle.LiveData
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourse(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourse(): LiveData<List<CourseEntity>>

    fun getCourseWithModule(courseId: String): LiveData<CourseEntity>

    fun getAllModuleByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent (courseId: String, moduleId: String) : LiveData<ModuleEntity>

}