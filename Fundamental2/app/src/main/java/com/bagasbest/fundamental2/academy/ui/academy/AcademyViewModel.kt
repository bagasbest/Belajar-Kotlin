package com.bagasbest.fundamental2.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourse() : List<CourseEntity> = DataDummy.generateDummyCourse()


}