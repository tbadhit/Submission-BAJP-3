package com.tbadhit.submission_bajp_1.ui.tvshow

import com.tbadhit.submission_bajp_1.data.ItemEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowEntity: ItemEntity
    private lateinit var emptyTvShowEntity: ItemEntity
    private val id = 0
    private val image =
        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"
    private val title = "The Good Doctor"
    private val description =
        "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?"
    private val genre = "Drama"
    private val releaseYear = "2017"
    private val duration = "42m"
    private val rate = 8.0

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
        tvShowEntity = ItemEntity(
            id = id,
            imagePath = image,
            backdropsImagePath = null,
            title = title,
            description = description,
            genre = genre,
            releaseYear = releaseYear,
            duration = duration,
            rate = rate,
        )
        emptyTvShowEntity = ItemEntity(
            id = null,
            imagePath = null,
            backdropsImagePath = null,
            title = null,
            description = null,
            genre = null,
            releaseYear = null,
            duration = null,
            rate = null,
        )
    }

    @Test
    fun testGetTvShows() {
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(10, tvShows.size)

    }

    @Test
    fun testGetTvShow() {
        assertEquals(tvShowEntity.id, viewModel.getTvShow(0).id)
        assertEquals(tvShowEntity.imagePath, viewModel.getTvShow(0).imagePath)
        assertEquals(tvShowEntity.title, viewModel.getTvShow(0).title)
        assertEquals(tvShowEntity.description, viewModel.getTvShow(0).description)
        assertEquals(tvShowEntity.genre, viewModel.getTvShow(0).genre)
        assertEquals(tvShowEntity.releaseYear, viewModel.getTvShow(0).releaseYear)
        assertEquals(tvShowEntity.duration, viewModel.getTvShow(0).duration)
    }

    @Test
    fun testGetEmptyTvShow() {
        assertEquals(emptyTvShowEntity.id,null)
        assertEquals(emptyTvShowEntity.imagePath, null)
        assertEquals(emptyTvShowEntity.title, null)
        assertEquals(emptyTvShowEntity.description, null)
        assertEquals(emptyTvShowEntity.genre, null)
        assertEquals(emptyTvShowEntity.releaseYear, null)
        assertEquals(emptyTvShowEntity.duration, null)
    }
}