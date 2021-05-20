package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bagasbest.beoskop21.GenerateDummyData
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SeriesViewModelTest {

    private var viewModel: SeriesViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var dataRepository = Mockito.mock(DataRepository::class.java)

    @Mock
    private lateinit var observer: Observer<List<ItemList>>


    @Before
    fun setUp(){
        viewModel = SeriesViewModel(dataRepository)
    }

    @Test
    fun getSeries() {
       val tvSeries = MutableLiveData<List<ItemList>>()
        tvSeries.value = GenerateDummyData.getDummyRemoteTvSeries()
        `when`(dataRepository.getTvSeries()).thenReturn(tvSeries)

        viewModel?.tvSeries()?.observeForever(observer)
        verify(observer).onChanged(GenerateDummyData.getDummyRemoteTvSeries())

        assertNotNull(tvSeries)
        assertEquals(1, tvSeries.value?.size)
    }


}