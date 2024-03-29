@file:Suppress("UNCHECKED_CAST")

package com.bagasbest.beoskop21.model.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTest {
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val countDownLatch = CountDownLatch(1)

        val observer = object : Observer<T>{
            override fun onChanged(t: T) {
                data[0] = t
                countDownLatch.countDown()
                liveData.removeObserver(this)
            }
        }

        liveData.observeForever(observer)
        try {
            countDownLatch.await(1500, TimeUnit.MILLISECONDS)
        } catch (error: Exception) {
            error.localizedMessage
        }
        return data[0] as T
    }
}