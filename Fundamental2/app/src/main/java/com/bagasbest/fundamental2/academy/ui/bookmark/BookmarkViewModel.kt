package com.bagasbest.fundamental2.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.source.AcademyRepository
import com.bagasbest.fundamental2.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks() : List<CourseEntity> = academyRepository.getBookmarkedCourse()

}