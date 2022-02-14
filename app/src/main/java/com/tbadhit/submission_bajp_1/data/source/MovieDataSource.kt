package com.tbadhit.submission_bajp_1.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.vo.Resource

interface MovieDataSource {
    fun getAllMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieById(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getAllTvShow(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShowById(tvShowId: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movieEntity: MovieEntity, state: Boolean)
}