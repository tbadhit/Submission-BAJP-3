package com.tbadhit.submission_bajp_1.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.utils.DataMovie
import com.tbadhit.submission_bajp_1.utils.DataTvShow
import com.tbadhit.submission_bajp_1.utils.EspressoIdlingResource
import org.junit.Before
import org.junit.Test
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import org.junit.After

class HomeActivityTest {
    private val dataMovies = DataMovie.generateDummyMovie()
    private val dataTvShows = DataTvShow.generateDummyTvShow()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadDataMovie() {
        assertDisplayed(R.id.rv_movie)
        clickListItem(R.id.rv_movie, dataMovies.size)
    }

    @Test
    fun loadDetailMovie() {
        clickListItem(R.id.rv_movie, 0)
        assertDisplayed(R.id.img_detail)
        assertDisplayed(R.id.img_backdrop)
        assertDisplayed(R.id.tv_title)
        assertDisplayed(R.id.tv_desc)
        assertDisplayed(R.id.tv_release_date)
        assertDisplayed(R.id.tv_language)
        assertDisplayed(R.id.tv_rate)
        assertDisplayed(R.id.tv_rate_count)
    }

    @Test
    fun loadDataTvShow() {
        clickOn("TV SHOW")
        assertDisplayed(R.id.rv_tv_show)
        clickListItem(R.id.rv_tv_show, dataTvShows.size)
    }

    @Test
    fun loadDetailTvShow() {
        clickOn("TV SHOW")
        clickListItem(R.id.rv_tv_show, 0)
        assertDisplayed(R.id.img_detail)
        assertDisplayed(R.id.img_backdrop)
        assertDisplayed(R.id.tv_title)
        assertDisplayed(R.id.tv_desc)
        assertDisplayed(R.id.tv_release_date)
        assertDisplayed(R.id.tv_language)
        assertDisplayed(R.id.tv_rate)
        assertDisplayed(R.id.tv_rate_count)
    }

    @Test
    fun loadFavoriteMovies() {
        clickListItem(R.id.rv_movie, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        clickOn(R.id.img_favorite)
        clickListItem(R.id.rv_favorite_movie, 0)

        assertDisplayed(R.id.img_detail)
        assertDisplayed(R.id.img_backdrop)
        assertDisplayed(R.id.tv_title)
        assertDisplayed(R.id.tv_desc)
        assertDisplayed(R.id.tv_release_date)
        assertDisplayed(R.id.tv_language)
        assertDisplayed(R.id.tv_rate)
        assertDisplayed(R.id.tv_rate_count)

        clickOn(R.id.fabFavorite)
        clickBack()
    }

    @Test
    fun loadFavoriteTvShow() {
        clickOn("TV SHOW")
        clickListItem(R.id.rv_tv_show, 0)
        clickOn(R.id.fabFavorite)
        clickBack()
        clickOn(R.id.img_favorite)
        clickOn("TV SHOW")
        clickListItem(R.id.rv_favorite_tv_show, 0)

        assertDisplayed(R.id.img_detail)
        assertDisplayed(R.id.img_backdrop)
        assertDisplayed(R.id.tv_title)
        assertDisplayed(R.id.tv_desc)
        assertDisplayed(R.id.tv_release_date)
        assertDisplayed(R.id.tv_language)
        assertDisplayed(R.id.tv_rate)
        assertDisplayed(R.id.tv_rate_count)

        clickOn(R.id.fabFavorite)
        clickBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }
}