package com.bagasbest.fundamental2.academy.ui.bookmark

import com.bagasbest.fundamental2.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
