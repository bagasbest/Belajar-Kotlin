package com.bagasbest.beoskop21.viewmodel.viewmodel

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class SeriesViewModelTest {

    private lateinit var viewModel: SeriesViewModel

    @Before
    fun setUp() {
        viewModel = SeriesViewModel()
    }

    @Test
    fun getSeries() {
        val movieModel = viewModel.getSeries()
        assertNotNull(movieModel)
        assertEquals(10, movieModel.size)
    }


}