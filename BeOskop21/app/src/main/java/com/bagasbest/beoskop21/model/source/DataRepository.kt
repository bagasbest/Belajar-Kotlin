package com.bagasbest.beoskop21.model.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bagasbest.beoskop21.model.source.local.LocalDataSource
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.source.remote.ApiResponse
import com.bagasbest.beoskop21.model.source.remote.RemoteDataSource
import com.bagasbest.beoskop21.model.source.remote.response.MovieResponse
import com.bagasbest.beoskop21.model.source.remote.response.SeriesResponse
import com.bagasbest.beoskop21.model.utils.AppExecutors
import com.bagasbest.beoskop21.model.vo.Resource

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mExecutors: AppExecutors,
) : DataSource {

    companion object {
        @Volatile
        private var INSTANCE: DataRepository? = null
        private val TAG = DataRepository::class.java.simpleName

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            mAppExecutors: AppExecutors
        ): DataRepository =
            INSTANCE ?: DataRepository(remoteDataSource, localDataSource, mAppExecutors).apply {
                INSTANCE = this
            }
    }

    override fun getMovieList(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(mExecutors) {

            override fun loadFromDb(): LiveData<PagedList<MovieEntity>> =
                LivePagedListBuilder(localDataSource.getMovieList(sort), config()).build()

            override fun shouldFetch(databaseFetch: PagedList<MovieEntity>?): Boolean =
                databaseFetch == null || databaseFetch.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = data.map { fetchData ->
                    MovieEntity(
                        id = fetchData.id,
                        posterPath = fetchData.posterPath,
                        title = fetchData.title,
                        overview = fetchData.overview,
                        releaseDate = fetchData.launchDate,
                        voteAverage = fetchData.userScore.toString(),
                        voteCount = fetchData.voteCount.toString(),
                        isFavorite = false
                    )
                }
                localDataSource.insertMovies(movieList)
            }

            override fun onFetchFailed() {
                Log.e(TAG, "error ketika load list data movie")
            }
        }.asLiveData()
    }


    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(mExecutors) {

            override fun loadFromDb(): LiveData<MovieEntity> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(databaseFetch: MovieEntity?): Boolean =
                databaseFetch == null || databaseFetch.title.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getDetailMovies(movieId)

            override fun saveCallResult(data: MovieResponse) {
                val movieDetail = MovieEntity(
                    id = data.id,
                    posterPath = data.posterPath,
                    title = data.title,
                    overview = data.overview,
                    releaseDate = data.launchDate,
                    voteAverage = data.userScore.toString(),
                    voteCount = data.voteCount.toString(),
                    isFavorite = false
                )
                localDataSource.updateMovies(movieDetail)
            }

            override fun onFetchFailed() {
                Log.e(TAG, "error ketika get detail movie")
            }
        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        LivePagedListBuilder(localDataSource.getFavoriteMovieList(), config()).build()


    override fun setFavoriteMovies(movie: MovieEntity, status: Boolean) {
        mExecutors.diskIO().execute {
            localDataSource.setFavoriteMovie(movie, status)
        }
    }

    override fun getSeriesList(sort: String): LiveData<Resource<PagedList<SeriesEntity>>> {

        return object :
            NetworkBoundResource<PagedList<SeriesEntity>, List<SeriesResponse>>(mExecutors) {

            override fun loadFromDb(): LiveData<PagedList<SeriesEntity>> =
                LivePagedListBuilder(localDataSource.getSeriesList(sort), config()).build()

            override fun shouldFetch(databaseFetch: PagedList<SeriesEntity>?): Boolean =
                databaseFetch == null || databaseFetch.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<SeriesResponse>>> =
                remoteDataSource.getSeries()

            override fun saveCallResult(data: List<SeriesResponse>) {
                val seriesList = data.map { fetchData ->
                    SeriesEntity(
                        id = fetchData.id,
                        posterPath = fetchData.posterPath,
                        name = fetchData.name,
                        overview = fetchData.overview,
                        firstAirDate = fetchData.firstAirDate,
                        voteAverage = fetchData.voteAverage.toString(),
                        voteCount = fetchData.voteCount.toString(),
                        isFavorite = false
                    )
                }
                localDataSource.insertSeries(seriesList)
            }

            override fun onFetchFailed() {
                Log.e(TAG, "error ketika get detail series")
            }
        }.asLiveData()
    }

    override fun getTvSeriesDetail(tvSeriesId: Int): LiveData<Resource<SeriesEntity>> {
        return object : NetworkBoundResource<SeriesEntity, SeriesResponse>(mExecutors) {

            override fun loadFromDb(): LiveData<SeriesEntity> =
                localDataSource.getSeriesById(tvSeriesId)

            override fun shouldFetch(databaseFetch: SeriesEntity?): Boolean =
                databaseFetch == null || databaseFetch.name.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<SeriesResponse>> =
                remoteDataSource.getDetailSeries(tvSeriesId)

            override fun saveCallResult(data: SeriesResponse) {
                val seriesDetail = SeriesEntity(
                    id = data.id,
                    posterPath = data.posterPath,
                    name = data.name,
                    overview = data.overview,
                    firstAirDate = data.firstAirDate,
                    voteAverage = data.voteAverage.toString(),
                    voteCount = data.voteCount.toString(),
                    isFavorite = false
                )
                localDataSource.updateSeries(seriesDetail)
            }

            override fun onFetchFailed() {
                Log.e(TAG, "error ketika get detail series")
            }
        }.asLiveData()
    }

    override fun getFavoriteSeries(): LiveData<PagedList<SeriesEntity>> =
        LivePagedListBuilder(localDataSource.getFavoriteSeriesList(), config()).build()

    override fun setFavoriteSeries(series: SeriesEntity, status: Boolean) {
        mExecutors.diskIO().execute {
            localDataSource.setFavoriteSeries(series, status)
        }
    }


    private fun config(): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
    }

}