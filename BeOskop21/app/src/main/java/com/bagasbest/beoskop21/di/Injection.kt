package com.bagasbest.beoskop21.di

import com.bagasbest.beoskop21.model.network.ApiConfig
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.RemoteDataSource

object Injection {
    fun movieRepository(): DataRepository {

        val remoteRepository = RemoteDataSource.getInstance(ApiConfig)

        return DataRepository.getInstance(remoteRepository)!!

    }
}