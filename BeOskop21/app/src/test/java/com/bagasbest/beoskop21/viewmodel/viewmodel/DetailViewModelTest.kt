package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bagasbest.beoskop21.GenerateDummyData
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.model.source.remote.response.TvSeriesDetail
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private var viewModel: DetailViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observerMovie: Observer<ItemList>

    @Mock
    private lateinit var observerTvSeries: Observer<TvSeriesDetail>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(dataRepository)
    }

    @Test
    fun getItemMovie() {
        val movie = MutableLiveData<ItemList>()
        movie.value = GenerateDummyData.getDummyRemoteMovie()[0]
        `when`(dataRepository.getMovieDetail(movie.value!!.id.toString())).thenReturn(movie)

        viewModel?.getMovieDetail(movie.value!!.id.toString())
            ?.observeForever(observerMovie)
        verify(observerMovie).onChanged(GenerateDummyData.getDummyRemoteMovie()[0])

        assertEquals(
            movie.value!!.id,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.id
        )
        assertEquals(
            movie.value!!.posterPath,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.posterPath
        )
        assertEquals(
            movie.value!!.title,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.title
        )
        assertEquals(
            movie.value!!.overview,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.overview
        )
        assertEquals(
            movie.value!!.launchDate,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.launchDate
        )
        assertEquals(
            movie.value!!.userScore,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.userScore
        )
        assertEquals(
            movie.value!!.voteCount,
            viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.voteCount
        )

    }

    @Test
    fun getItemSeries() {
        val tvSeries = MutableLiveData<TvSeriesDetail>()
        tvSeries.value = GenerateDummyData.getDummyRemoteTvSeriesDetail()
        `when`(dataRepository.getTvSeriesDetail(tvSeries.value!!.id.toString()))
            .thenReturn(tvSeries)

        viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())
            ?.observeForever(observerTvSeries)
        verify(observerTvSeries).onChanged(GenerateDummyData.getDummyRemoteTvSeriesDetail())

        assertEquals(
            tvSeries.value!!.id,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.id
        )
        assertEquals(
            tvSeries.value!!.name,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.name
        )
        assertEquals(
            tvSeries.value!!.firstAirDate,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.firstAirDate
        )
        assertEquals(
            tvSeries.value!!.overview,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.overview
        )
        assertEquals(
            tvSeries.value!!.voteAverage,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.voteAverage
        )
        assertEquals(
            tvSeries.value!!.voteCount,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.voteCount
        )
        assertEquals(
            tvSeries.value!!.posterPath,
            viewModel?.getTvSeriesDetail(tvSeries.value!!.id.toString())?.value?.posterPath
        )
    }
}