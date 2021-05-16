package com.bagasbest.beoskop21.model.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bagasbest.beoskop21.GenerateDummyData
import com.bagasbest.beoskop21.model.source.remote.RemoteDataSource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class DataRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val fakeDataRepository = FakeDataRepository(remoteDataSource)
    private val listOfMovie = GenerateDummyData.getDummyRemoteMovie()
    private val movieId = GenerateDummyData.getDummyRemoteMovie()[0].id.toString()
    private val listOfTvSeries = GenerateDummyData.getDummyRemoteTvSeries()
    private val tvSeriesId = listOfTvSeries[0].id.toString()
    private val tvSeriesDetail = GenerateDummyData.getDummyRemoteTvSeriesDetail()

    // sebagian kecil kode dibawah, saya pelajari dari repository: KylixEza/Submission-BAJP2-Dicoding, dan saya modifikasi sesuai dengan requirement proyek saya
    // todo Link: https://github.com/KylixEza/Submission-BAJP2-Dicoding/blob/master/app/src/test/java/com/kylix/submissionbajp2/repository/DataRepositoryTest.kt
    private fun <T> anyOfType(type: Class<T>): T = any(type)
    private fun <T> equalOfType(obj: T): T = eq(obj)

    @Test
    fun getMovie() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetMovieCallback
            callback.onResponse(listOfMovie)
            null
        }.`when`(remoteDataSource)
            .getMovie(anyOfType(RemoteDataSource.GetMovieCallback::class.java))

        val result = LiveDataTest.getValue(fakeDataRepository.getMovie())
        assertEquals(listOfMovie.size, result.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetMovieDetailCallback
            callback.onResponse(listOfMovie[0])
            null
        }.`when`(remoteDataSource)
            .getMovieDetail(
                equalOfType(movieId),
                anyOfType(RemoteDataSource.GetMovieDetailCallback::class.java)
            )
    }

    @Test
    fun getTvSeries() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetTvSeriesCallback
            callback.onResponse(listOfTvSeries)
            null
        }.`when`(remoteDataSource)
            .getTvSeries(anyOfType(RemoteDataSource.GetTvSeriesCallback::class.java))

        val result = LiveDataTest.getValue(fakeDataRepository.getTvSeries())
        assertEquals(listOfTvSeries.size, result.size)
    }

    @Test
    fun getTvSeriesDetail() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.GetTvSeriesDetailCallback
            callback.onResponse(tvSeriesDetail)
            null
        }.`when`(remoteDataSource)
            .getTvSeriesDetail(
                equalOfType(tvSeriesId),
                anyOfType(RemoteDataSource.GetTvSeriesDetailCallback::class.java)
            )
    }
}