package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bagasbest.beoskop21.GenerateDummyData
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity

import com.bagasbest.beoskop21.model.vo.Resource
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private var viewModel: DetailViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerSeries: Observer<Resource<SeriesEntity>>

    private val dummyMovies = GenerateDummyData.getDummyRemoteMovieDetail()
    private val dummyMoviesId = dummyMovies.id
    private val dummySeries = GenerateDummyData.getDummyRemoteTvSeriesDetail()
    private val dummySeriesId = dummySeries.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovies = Resource.success(dummyMovies)
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = dummyMovies
        `when`(dataRepository.getMovieDetail(dummyMoviesId)).thenReturn(movies)
        viewModel?.getMovieDetail(dummyMoviesId)
        verify(dataRepository).getMovieDetail(dummyMoviesId)
        viewModel?.getDetailMovie()?.observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovies)
    }

    @Test
    fun getDetailSeries() {
        val dummySeries = Resource.success(dummySeries)
        val series = MutableLiveData<Resource<SeriesEntity>>()
        series.value = dummySeries
        `when`(dataRepository.getTvSeriesDetail(dummySeriesId)).thenReturn(series)
        viewModel?.getTvSeriesDetail(dummySeriesId)
        verify(dataRepository).getTvSeriesDetail(dummySeriesId)
        viewModel?.getDetailSeries()?.observeForever(observerSeries)
        verify(observerSeries).onChanged(dummySeries)
    }

    @Test
    fun setFavoriteMovie() {
        val setDetailMovie = Resource.success(dummyMovies)
        val movies = MutableLiveData<Resource<MovieEntity>>()
        movies.value = setDetailMovie
        `when`(dataRepository.getMovieDetail(dummyMoviesId)).thenReturn(movies)
        viewModel?.getMovieDetail(dummyMoviesId)
        viewModel?.setFavoriteMovie()
        verify(dataRepository).setFavoriteMovies(movies.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(observerMovie)
    }

    @Test
    fun setFavoriteSeries() {
        val setDetailSeries = Resource.success(dummySeries)
        val series = MutableLiveData<Resource<SeriesEntity>>()
        series.value = setDetailSeries
        `when`(dataRepository.getTvSeriesDetail(dummySeriesId)).thenReturn(series)
        viewModel?.getTvSeriesDetail(dummySeriesId)
        viewModel?.setFavoriteSeries()
        verify(dataRepository).setFavoriteSeries(series.value!!.data as SeriesEntity, true)
        verifyNoMoreInteractions(observerSeries)
    }
}