package com.bagasbest.fundamental2.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.academy.data.source.AcademyRepository

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse () : CourseEntity = academyRepository.getCourseWithModule(courseId)

    fun getModules() : List<ModuleEntity> = academyRepository.getAllModuleByCourse(courseId)

}