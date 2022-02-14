package com.tbadhit.submission_bajp_1.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tbadhit.submission_bajp_1.data.source.local.LocalDataSource
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.data.source.remote.ApiResponse
import com.tbadhit.submission_bajp_1.data.source.remote.RemoteDataSource
import com.tbadhit.submission_bajp_1.data.source.remote.response.MovieItem
import com.tbadhit.submission_bajp_1.data.source.remote.response.TvShowItem
import com.tbadhit.submission_bajp_1.utils.AppExecutors
import com.tbadhit.submission_bajp_1.vo.Resource

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {
    override fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllMovies(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> = remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieItem>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.overview,
                        response.originalLanguage,
                        response.title,
                        response.posterPath,
                        response.backdropPath,
                        response.releaseDate,
                        response.voteAverage,
                        response.voteCount,
                        isFavorite = false,
                        isTvShow = false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()
    }

    override fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<MovieItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<List<MovieItem>>> = remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieItem>) {
                lateinit var movieEntity: MovieEntity
                for (movie in data) {
                    if (movieId == movie.id) {
                        movieEntity = MovieEntity(
                            movie.id,
                            movie.overview,
                            movie.originalLanguage,
                            movie.title,
                            movie.posterPath,
                            movie.backdropPath,
                            movie.releaseDate,
                            movie.voteAverage,
                            movie.voteCount,
                            isFavorite = false,
                            isTvShow = false
                        )
                    }
                }
                localDataSource.updateMovie(movieEntity)
            }

        }.asLiveData()
    }

    override fun getAllTvShow(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<TvShowItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> = remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<TvShowItem>) {
                val tvShowList = ArrayList<MovieEntity>()
                for (response in data) {
                    val tvShow = MovieEntity(
                        response.id,
                        response.overview,
                        response.originalLanguage,
                        response.name,
                        response.posterPath,
                        response.backdropPath.toString(),
                        response.firstAirDate,
                        response.voteAverage,
                        response.voteCount,
                        isFavorite = false,
                        isTvShow = true
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertMovie(tvShowList)
            }

        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, List<TvShowItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getTvShow(tvShowId)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowItem>>> {
                return remoteDataSource.getTvShow()
            }

            override fun saveCallResult(data: List<TvShowItem>) {
                lateinit var movieEntity: MovieEntity
                for (movie in data) {
                    if (tvShowId == movie.id) {
                        movieEntity = MovieEntity(
                            movie.id,
                            movie.overview,
                            movie.originalLanguage,
                            movie.name,
                            movie.posterPath,
                            movie.backdropPath.toString(),
                            movie.firstAirDate,
                            movie.voteAverage,
                            movie.voteCount,
                            isFavorite = false,
                            isTvShow = true
                        )
                    }
                }
                localDataSource.updateMovie(movieEntity)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFavoriteTvShows(), config).build()
    }

    override fun setMovieFavorite(movieEntity: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.setMovieFavorite(movieEntity, state)
        }
    }
}