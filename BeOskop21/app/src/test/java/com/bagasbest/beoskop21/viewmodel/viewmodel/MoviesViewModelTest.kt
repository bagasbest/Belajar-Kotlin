package com.bagasbest.beoskop21.viewmodel.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movieModel = viewModel.getMovies()
        assertNotNull(movieModel)
        assertEquals(10, movieModel.size)
    }
}