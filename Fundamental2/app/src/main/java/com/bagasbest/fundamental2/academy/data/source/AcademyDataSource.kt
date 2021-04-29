package com.bagasbest.fundamental2.academy.data.source

import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourse(): List<CourseEntity>

    fun getBookmarkedCourse(): List<CourseEntity>

    fun getCourseWithModule(courseId: String): CourseEntity

    fun getAllModuleByCourse(courseId: String): List<ModuleEntity>

    fun getContent (courseId: String, moduleId: String) : ModuleEntity

}