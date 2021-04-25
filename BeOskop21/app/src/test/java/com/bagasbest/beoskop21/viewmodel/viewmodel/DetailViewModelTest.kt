package com.bagasbest.beoskop21.viewmodel.viewmodel

import com.bagasbest.beoskop21.model.utils.DummyData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DummyData.generateDummyMovie()[0]
    private val dummySeries = DummyData.generateDummyTvSeries()[0]
    private val movieTitle = dummyMovie.title
    private val seriesTitle = dummySeries.title


    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getItemMovie() {
        if (movieTitle != null) {
            viewModel.setSelectedItems(movieTitle)
        }
        val movie = viewModel.getItemMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.description, movie.description)
        assertEquals(dummyMovie.launchDate, movie.launchDate)
        assertEquals(dummyMovie.poster, movie.poster)
        assertEquals(dummyMovie.duration, movie.duration)
        assertEquals(dummyMovie.director, movie.director)
        assertEquals(dummyMovie.pgRating, movie.pgRating)
        assertEquals(dummyMovie.userScore, movie.userScore)
    }

    @Test
    fun getItemSeries() {
        if (seriesTitle != null) {
            viewModel.setSelectedItems(seriesTitle)
        }
        val series = viewModel.getItemSeries()
        assertNotNull(series)
        assertEquals(dummySeries.title, series.title)
        assertEquals(dummySeries.description, series.description)
        assertEquals(dummySeries.year, series.year)
        assertEquals(dummySeries.poster, series.poster)
        assertEquals(dummySeries.durationEpisode, series.durationEpisode)
        assertEquals(dummySeries.creator, series.creator)
        assertEquals(dummySeries.pgRating, series.pgRating)
        assertEquals(dummySeries.userScore, series.userScore)
        assertNotNull(dummySeries.genre, series.genre)
        assertNotNull(dummySeries.streamingOn, series.streamingOn)
    }
}