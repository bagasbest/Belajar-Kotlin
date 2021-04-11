package com.bagasbest.fundamental2.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmarks() : List<CourseEntity> = DataDummy.generateDummyCourse()

}