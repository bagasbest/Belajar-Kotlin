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
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class SeriesViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var viewModel: SeriesViewModel? = null
    private var dataRepository = Mockito.mock(DataRepository::class.java)



    @Before
    fun setUp(){
        viewModel = SeriesViewModel(dataRepository)
    }

    @Test
    fun getSeries() {
       val tvSeries = MutableLiveData<List<ItemList>>()
        tvSeries.value = GenerateDummyData.getDummyRemoteTvSeries()
        `when`(dataRepository.getTvSeries()).thenReturn(tvSeries)

        val observer = Mockito.mock(Observer::class.java)
        viewModel?.tvSeries?.observeForever(observer as Observer<List<ItemList>>)
        Mockito.verify(dataRepository).getTvSeries()

        assertNotNull(tvSeries)
        assertEquals(1, tvSeries.value?.size)
    }


}