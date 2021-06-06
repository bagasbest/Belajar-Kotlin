package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.bagasbest.beoskop21.model.source.DataRepository
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
    private lateinit var observer: Observer<Resource<PagedList<SeriesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<SeriesEntity>

    @Before
    fun setUp(){
        viewModel = SeriesViewModel(dataRepository)
    }

    @Test
    fun getSeries() {
        val dummySeries = Resource.success(pagedList)
        `when`(dummySeries.data?.size).thenReturn(1)
        val series = MutableLiveData<Resource<PagedList<SeriesEntity>>>()
        series.value = dummySeries

        `when`(dataRepository.getSeriesList("OLDEST")).thenReturn(series)
        val seriesEntities = viewModel?.tvSeries("OLDEST")?.value?.data
        verify(dataRepository).getSeriesList("OLDEST")

        assertNotNull(seriesEntities)
        assertEquals(1, seriesEntities?.size)

        viewModel?.tvSeries("OLDEST")?.observeForever(observer)
        verify(observer).onChanged(dummySeries)
    }


}