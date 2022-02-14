package com.tbadhit.submission_bajp_1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tbadhit.submission_bajp_1.data.source.MovieRepository
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private val movieId = MutableLiveData<Int>()
    private val tvShowId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var movie: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) {movieId ->
            movieRepository.getMovieById(movieId)
        }

    var tvShow: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(tvShowId) {tvShowId ->
            movieRepository.getTvShowById(tvShowId)
        }

    fun setFavoriteMovie() {
        val movieResource = movie.value?.data
        if (movieResource != null) {
            val newState = !movieResource.isFavorite
            movieRepository.setMovieFavorite(movieResource, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShowResource = tvShow.value?.data
        if (tvShowResource != null) {
            val newState = !tvShowResource.isFavorite
            movieRepository.setMovieFavorite(tvShowResource, newState)
        }
    }
}