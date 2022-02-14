package com.tbadhit.submission_bajp_1.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tbadhit.submission_bajp_1.data.source.MovieRepository
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllMovies(sort)
}