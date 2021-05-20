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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dataRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<ItemList>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(dataRepository)
    }


    @Test
    fun getMovies() {
        // sebagian kecil kode dibawah, saya pelajari dari repository: KylixEza/Submission-BAJP2-Dicoding, dan saya modifikasi sesuai dengan requirement proyek saya
        // todo Link: https://github.com/KylixEza/Submission-BAJP2-Dicoding
        val movie = MutableLiveData<List<ItemList>>()
        movie.value = GenerateDummyData.getDummyRemoteMovie()
        `when`(dataRepository.getMovie()).thenReturn(movie)

        viewModel.movie().observeForever(observer)
        verify(observer).onChanged(GenerateDummyData.getDummyRemoteMovie())

        assertNotNull(movie)
        assertEquals(1, movie.value?.size)
    }


}