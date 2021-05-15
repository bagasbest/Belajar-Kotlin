package com.bagasbest.beoskop21.viewmodel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.beoskop21.di.Injection
import com.bagasbest.beoskop21.model.source.DataRepository


class ViewModelFactory(private val dataRepository: DataRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if(instance == null) {
                synchronized(ViewModelFactory::class.java) {
                    if(instance == null) {
                        instance = ViewModelFactory(Injection.movieRepository())
                    }
                }
            }
         return instance
        }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(dataRepository) as T
            }

            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                SeriesViewModel(dataRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(dataRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}