package com.bagasbest.fundamental2.academy.di

import android.content.Context
import com.bagasbest.fundamental2.academy.data.source.AcademyRepository
import com.bagasbest.fundamental2.academy.data.source.remote.RemoteDataSource
import com.bagasbest.fundamental2.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : AcademyRepository {

        val remoteDateSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDateSource)
    }
}