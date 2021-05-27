package com.bagasbest.fundamental2.academy.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.bagasbest.fundamental2.academy.data.CourseEntity
import com.bagasbest.fundamental2.academy.data.ModuleEntity

data class CourseWithModule(
    @Embedded
    var mCourse: CourseEntity,

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var mModules: List<ModuleEntity>

)