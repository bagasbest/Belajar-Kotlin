package com.bagasbest.beoskop21.model.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.bagasbest.beoskop21.model.source.remote.ApiResponse
import com.bagasbest.beoskop21.model.source.remote.StatusResponse
import com.bagasbest.beoskop21.model.utils.AppExecutors
import com.bagasbest.beoskop21.model.vo.Resource



abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    
    private val result = MediatorLiveData<Resource<ResultType>>()
    
    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        
        result.addSource(dbSource) { databaseFetch ->
            result.removeSource(dbSource)
            if(shouldFetch(databaseFetch)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { addNew ->
                    result.value = Resource.success(addNew)
                }
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }


        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                   mExecutors.diskIO().execute {
                       saveCallResult(response.body)
                       mExecutors.mainThread().execute {
                           result.addSource(loadFromDb()) {newData ->
                               result.value = Resource.success(newData)
                           }
                       }
                   }


                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                    result.addSource(loadFromDb()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }

                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }




    fun asLiveData() : LiveData<Resource<ResultType>> = result

    abstract fun onFetchFailed()

    abstract fun saveCallResult(data: RequestType)

    abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    abstract fun shouldFetch(databaseFetch: ResultType?): Boolean

    abstract fun loadFromDb(): LiveData<ResultType>
}