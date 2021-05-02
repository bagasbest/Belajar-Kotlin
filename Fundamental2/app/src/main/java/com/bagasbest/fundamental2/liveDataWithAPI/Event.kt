package com.bagasbest.fundamental2.liveDataWithAPI

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled(): T? {
        return if(hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}