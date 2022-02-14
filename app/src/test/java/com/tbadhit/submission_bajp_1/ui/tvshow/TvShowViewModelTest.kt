package com.tbadhit.submission_bajp_1.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.tbadhit.submission_bajp_1.data.source.MovieRepository
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.utils.SortUtils
import com.tbadhit.submission_bajp_1.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var tvShowViewModel: TvShowViewModel
    private val sort = SortUtils.NEWEST

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        tvShowViewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dataDummyTvShow = Resource.success(pagedList)
        `when`(dataDummyTvShow.data?.size).thenReturn(2)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dataDummyTvShow

        `when`(movieRepository.getAllTvShow(sort)).thenReturn(movie)
        val movieEntities = tvShowViewModel.getTvShows(sort).value?.data
        verify(movieRepository).getAllTvShow(sort)
        assertNotNull(movieEntities)
        assertEquals(2, movieEntities?.size)

        tvShowViewModel.getTvShows(sort).observeForever(observer)
        verify(observer).onChanged(dataDummyTvShow)
    }

}