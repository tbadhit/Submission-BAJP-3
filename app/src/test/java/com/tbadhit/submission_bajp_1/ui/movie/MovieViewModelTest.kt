package com.tbadhit.submission_bajp_1.ui.movie

import com.tbadhit.submission_bajp_1.data.ItemEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieEntity: ItemEntity
    private lateinit var emptyMovieEntity: ItemEntity
    private val id = 0
    private val image =
        "https://www.themoviedb.org/t/p/w1280/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
    private val title = "Spider-Man: No Way Home"
    private val description =
        "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man."
    private val genre = "Action, Adventure, Science Fiction"
    private val releaseYear = "2021"
    private val duration = "2h 28m"
    private val rate = 9.7

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
        movieEntity = ItemEntity(
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
        emptyMovieEntity = ItemEntity(
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
    fun testGetMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }

    @Test
    fun testGetMovie() {
        assertEquals(movieEntity.id, viewModel.getMovie(0).id)
        assertEquals(movieEntity.imagePath, viewModel.getMovie(0).imagePath)
        assertEquals(movieEntity.title, viewModel.getMovie(0).title)
        assertEquals(movieEntity.description, viewModel.getMovie(0).description)
        assertEquals(movieEntity.genre, viewModel.getMovie(0).genre)
        assertEquals(movieEntity.releaseYear, viewModel.getMovie(0).releaseYear)
        assertEquals(movieEntity.duration, viewModel.getMovie(0).duration)
    }

    @Test
    fun testGetEmptyMovie() {
        assertEquals(emptyMovieEntity.id,null)
        assertEquals(emptyMovieEntity.imagePath, null)
        assertEquals(emptyMovieEntity.title, null)
        assertEquals(emptyMovieEntity.description, null)
        assertEquals(emptyMovieEntity.genre, null)
        assertEquals(emptyMovieEntity.releaseYear, null)
        assertEquals(emptyMovieEntity.duration, null)
    }
}