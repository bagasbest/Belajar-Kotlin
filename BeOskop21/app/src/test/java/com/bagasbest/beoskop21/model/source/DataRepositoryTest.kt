package com.bagasbest.beoskop21.model.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bagasbest.beoskop21.GenerateDummyData
import com.bagasbest.beoskop21.model.source.local.LocalDataSource
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.source.remote.RemoteDataSource
import com.bagasbest.beoskop21.model.utils.AppExecutors
import com.bagasbest.beoskop21.model.utils.DummyData
import com.bagasbest.beoskop21.model.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DataRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutor = mock(AppExecutors::class.java)
    private val fakeDataRepository = FakeDataRepository(remoteDataSource, localDataSource, appExecutor)
    private val listOfMovie = DummyData.generateDummyMovie()
    private val movieId = GenerateDummyData.getDummyRemoteMovie()[0].id
    private val listOfTvSeries = DummyData.generateDummyTvSeries()
    private val tvSeriesId = GenerateDummyData.getDummyRemoteTvSeriesDetail().id


    @Test
    fun getMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getMovieList("OLDEST")).thenReturn(dataSourceFactory)
        fakeDataRepository.getMovieList("OLDEST")

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovie()))
        verify(localDataSource).getMovieList("OLDEST")
        assertNotNull(movieEntities.data)
        assertEquals(listOfMovie.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
       val dummyMovies = MutableLiveData<MovieEntity>()
        dummyMovies.value = GenerateDummyData.getDummyRemoteMovieDetail()
        `when`(localDataSource.getMovieById(movieId)).thenReturn(dummyMovies)

        val movieDetailEntities = LiveDataTest.getValue(fakeDataRepository.getMovieDetail(movieId))
        verify(localDataSource).getMovieById(movieId)
        assertNotNull(movieDetailEntities)
        assertEquals(GenerateDummyData.getDummyRemoteMovieDetail().id, movieDetailEntities.data?.id)
    }

    @Test
    fun getTvSeries() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, SeriesEntity>
        `when`(localDataSource.getSeriesList("OLDEST")).thenReturn(dataSourceFactory)
        fakeDataRepository.getSeriesList("OLDEST")

        val seriesEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyTvSeries()))
        verify(localDataSource).getSeriesList("OLDEST")
        assertNotNull(seriesEntities.data)
        assertEquals(listOfTvSeries.size.toLong(), seriesEntities.data?.size?.toLong())
    }

    @Test
    fun getTvSeriesDetail() {
        val dummySeries = MutableLiveData<SeriesEntity>()
        dummySeries.value = GenerateDummyData.getDummyRemoteTvSeriesDetail()
        `when`(localDataSource.getSeriesById(tvSeriesId)).thenReturn(dummySeries)

        val seriesDetailEntities = LiveDataTest.getValue(fakeDataRepository.getTvSeriesDetail(tvSeriesId))
        verify(localDataSource).getSeriesById(tvSeriesId)
        assertNotNull(seriesDetailEntities)
        assertEquals(GenerateDummyData.getDummyRemoteTvSeriesDetail().id, seriesDetailEntities.data?.id)
    }
}