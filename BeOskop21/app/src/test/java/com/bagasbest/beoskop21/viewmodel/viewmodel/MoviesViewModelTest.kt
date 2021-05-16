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

class MoviesViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var viewModel: MoviesViewModel? = null
    private var dataRepository = Mockito.mock(DataRepository::class.java)


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

        val observer = Mockito.mock(Observer::class.java)
        viewModel?.movie?.observeForever(observer as Observer<List<ItemList>>)
        Mockito.verify(dataRepository).getMovie()

        assertNotNull(movie)
        assertEquals(1, movie.value?.size)
    }


}