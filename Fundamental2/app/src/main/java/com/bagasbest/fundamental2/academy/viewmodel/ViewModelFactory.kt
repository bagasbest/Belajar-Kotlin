package com.bagasbest.fundamental2.academy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.fundamental2.academy.data.source.AcademyRepository
import com.bagasbest.fundamental2.academy.di.Injection
import com.bagasbest.fundamental2.academy.ui.academy.AcademyViewModel
import com.bagasbest.fundamental2.academy.ui.bookmark.BookmarkViewModel
import com.bagasbest.fundamental2.academy.ui.detail.DetailCourseViewModel
import com.bagasbest.fundamental2.academy.ui.reader.CourseReaderViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}