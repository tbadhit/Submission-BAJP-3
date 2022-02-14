package com.tbadhit.submission_bajp_1.utils

import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.data.source.remote.response.TvShowItem

object DataTvShow {
    fun generateDummyTvShow(): List<MovieEntity> {
        val tvShow = ArrayList<MovieEntity>()
        tvShow.add(
            MovieEntity(
                77169,
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "en",
                "Cobra Kai",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                "2018-05-02",
                8.1,
                1529,
            )
        )
        tvShow.add(
            MovieEntity(
                79242,
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "en",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
                "2018-10-26",
                8.4,
                1988
            )
        )
        return tvShow
    }

    fun generateRemoteDummyTvShow(): List<TvShowItem> {
        val tvShow = ArrayList<TvShowItem>()
        tvShow.add(
            TvShowItem(
                "2018-05-02",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "en",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                8.1,
                77169,
                "Cobra Kai",
                1529,
            )
        )
        tvShow.add(
            TvShowItem(
                "2018-10-26",
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "en",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
                8.4,
                79242,
                "Chilling Adventures of Sabrina",
                1988
            )
        )
        return tvShow
    }
}