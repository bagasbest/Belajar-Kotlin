package com.bagasbest.fundamental2.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bagasbest.fundamental2.academy.data.source.local.entity.CourseEntity
import com.bagasbest.fundamental2.academy.data.source.AcademyRepository
import com.bagasbest.fundamental2.academy.vo.Resource


class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getCourses(): LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getAllCourses()
}