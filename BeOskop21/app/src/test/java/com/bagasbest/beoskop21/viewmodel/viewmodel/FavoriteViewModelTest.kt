package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bagasbest.beoskop21.GenerateDummyData
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerMovies: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerSeries: Observer<PagedList<SeriesEntity>>

    @Mock
    private lateinit var pagedListMovies: PagedList<MovieEntity>

    @Mock
    private lateinit var pagedListSeries: PagedList<SeriesEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(dataRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = pagedListMovies
        `when`(dummyMovies.size).thenReturn(1)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(dataRepository.getFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovies().value
        verify(dataRepository).getFavoriteMovies()

        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteMovies() {
        viewModel.setFavoriteMovies(GenerateDummyData.getDummyRemoteMovieDetail())
        verify(dataRepository).setFavoriteMovies(GenerateDummyData.getDummyRemoteMovieDetail(), true)
        verifyNoMoreInteractions(dataRepository)
    }

    @Test
    fun getFavoriteSeries() {
        val dummySeries = pagedListSeries
        `when`(dummySeries.size).thenReturn(1)
        val series = MutableLiveData<PagedList<SeriesEntity>>()
        series.value = dummySeries

        `when`(dataRepository.getFavoriteSeries()).thenReturn(series)
        val seriesEntities = viewModel.getFavoriteSeries().value
        verify(dataRepository).getFavoriteSeries()

        assertNotNull(seriesEntities)
        assertEquals(1, seriesEntities?.size)

        viewModel.getFavoriteSeries().observeForever(observerSeries)
        verify(observerSeries).onChanged(dummySeries)
    }

    @Test
    fun setFavoriteSeries() {
        viewModel.setFavoriteSeries(GenerateDummyData.getDummyRemoteTvSeriesDetail())
        verify(dataRepository).setFavoriteSeries(GenerateDummyData.getDummyRemoteTvSeriesDetail(), true)
        verifyNoMoreInteractions(dataRepository)
    }
}