package com.tbadhit.submission_bajp_1.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.utils.DataMovie
import com.tbadhit.submission_bajp_1.utils.DataTvShow
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dataMovies = DataMovie.generateDummyMovie()
    private val dataTvShows = DataTvShow.generateDummyTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun loadDataMovie() {
        onView(withId(R.id.rv_movie))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMovies.size
            )
        )
    }

    @Test
    fun loadDataTvShow() {
        onView(withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_show)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataTvShows.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.img_detail)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withContentDescription("Gambar poster")))
        }
        onView(withId(R.id.tv_title_detail)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataMovies[0].title)))
        }
        onView(withId(R.id.tv_genre)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataMovies[0].genre)))
        }
        onView(withId(R.id.tv_year)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataMovies[0].releaseYear)))
        }
        onView(withId(R.id.tv_rate)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataMovies[0].rate.toString())))
        }
        onView(withId(R.id.tv_duration)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataMovies[0].duration)))
        }
        onView(withId(R.id.tv_desc_detail)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataMovies[0].description)))
        }
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.img_detail)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withContentDescription("Gambar poster")))
        }
        onView(withId(R.id.tv_title_detail)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataTvShows[0].title)))
        }
        onView(withId(R.id.tv_genre)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataTvShows[0].genre)))
        }
        onView(withId(R.id.tv_year)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataTvShows[0].releaseYear)))
        }
        onView(withId(R.id.tv_rate)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataTvShows[0].rate.toString())))
        }
        onView(withId(R.id.tv_duration)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataTvShows[0].duration)))
        }
        onView(withId(R.id.tv_desc_detail)).apply {
            check(ViewAssertions.matches(isDisplayed()))
            check(ViewAssertions.matches(withText(dataTvShows[0].description)))
        }
    }
}