package com.bagasbest.fundamental2.academy.di

import android.content.Context
import com.bagasbest.fundamental2.academy.data.source.AcademyRepository
import com.bagasbest.fundamental2.academy.data.source.local.LocalDataSource
import com.bagasbest.fundamental2.academy.data.source.local.room.AcademyDatabase
import com.bagasbest.fundamental2.academy.data.source.remote.RemoteDataSource
import com.bagasbest.fundamental2.academy.utils.AppExecutors
import com.bagasbest.fundamental2.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDateSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val apiExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDateSource, localDataSource, apiExecutors)
    }
}