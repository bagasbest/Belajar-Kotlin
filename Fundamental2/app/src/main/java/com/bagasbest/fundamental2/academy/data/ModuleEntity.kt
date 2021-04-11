package com.bagasbest.fundamental2.academy.data

data class ModuleEntity(
    var moduleId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false,
) {
    var contentEntity: ContentEntity? = null
}