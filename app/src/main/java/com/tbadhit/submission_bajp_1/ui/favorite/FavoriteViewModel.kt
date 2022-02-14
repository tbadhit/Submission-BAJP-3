package com.tbadhit.submission_bajp_1.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tbadhit.submission_bajp_1.data.source.MovieRepository
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity

class FavoriteViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getFavoriteMovies()

    fun getFavoriteTvShows(): LiveData<PagedList<MovieEntity>> =
        movieRepository.getFavoriteTvShows()

    fun setFavoriteDataMovie(movieEntity: MovieEntity) {
        val newState = !movieEntity.isFavorite
        movieRepository.setMovieFavorite(movieEntity, newState)
    }
}