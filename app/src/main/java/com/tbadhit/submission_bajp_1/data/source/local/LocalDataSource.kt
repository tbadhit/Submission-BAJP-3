package com.tbadhit.submission_bajp_1.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.data.source.local.room.MovieDao
import com.tbadhit.submission_bajp_1.utils.SortUtils

class LocalDataSource(private val mMovieDao: MovieDao) {

    fun getAllMovies(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryMovies(sort)
        return mMovieDao.getAllMovies(query)
    }

    fun getAllTvShows(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQueryTvShows(sort)
        return mMovieDao.getAllTvShow(query)
    }

    fun getAllFavoriteMovies(): DataSource.Factory<Int, MovieEntity> {
        return mMovieDao.getFavoriteMovies()
    }

    fun getAllFavoriteTvShows(): DataSource.Factory<Int, MovieEntity> {
        return mMovieDao.getFavoriteTvShows()
    }

    fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieDao.updateDataMovie(movie)
    }

    fun getMovie(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovieById(movieId)

    fun getTvShow(tvShowId: Int): LiveData<MovieEntity> = mMovieDao.getTvShowById(tvShowId)

    fun insertMovie(movies: List<MovieEntity>) = mMovieDao.insertDataMovie(movies)

    fun updateMovie(movie: MovieEntity) = mMovieDao.updateDataMovie(movie)

}