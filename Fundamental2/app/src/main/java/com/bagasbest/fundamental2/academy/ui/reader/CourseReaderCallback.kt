package com.bagasbest.fundamental2.academy.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}