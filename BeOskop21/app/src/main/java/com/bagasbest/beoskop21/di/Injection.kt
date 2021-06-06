package com.bagasbest.beoskop21.di

import android.content.Context
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.LocalDataSource
import com.bagasbest.beoskop21.model.source.local.room.BeOskopDatabase
import com.bagasbest.beoskop21.model.source.remote.RemoteDataSource
import com.bagasbest.beoskop21.model.utils.AppExecutors

object Injection {
    fun provideDataRepository(context: Context): DataRepository {
        val localDataSource = LocalDataSource(BeOskopDatabase.getInstance(context).beOskopDao())
        val remoteDataSource = RemoteDataSource.getInstance()
        val appExecutors = AppExecutors()

        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}