package com.tbadhit.submission_bajp_1.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.tbadhit.submission_bajp_1.data.source.MovieRepository
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvShows(sort: String): LiveData<Resource<PagedList<MovieEntity>>> = movieRepository.getAllTvShow(sort)
}