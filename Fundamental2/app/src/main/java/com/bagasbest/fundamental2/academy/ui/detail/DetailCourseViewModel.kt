package com.bagasbest.fundamental2.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.ModuleEntity
import com.bagasbest.fundamental2.academy.utils.DataDummy

class DetailCourseViewModel : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse () : CourseEntity {
        lateinit var course: CourseEntity
        val courseEntities = DataDummy.generateDummyCourse()
        for(courseEntity in courseEntities) {
            if(courseEntity.courseId == courseId) {
                course = courseEntity
            }
        }
        return course
    }


    fun getModules() : List<ModuleEntity> = DataDummy.generateDummyModules(courseId)

}