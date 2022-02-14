package com.tbadhit.submission_bajp_1.utils

import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.data.source.remote.response.MovieItem

object DataMovie {
    fun generateDummyMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                464052,
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "en",
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-16",
                7.3,
                2255
            )
        )
        movies.add(
            MovieEntity(
                508442,
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "en",
                "Soul",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-25",
                8.4,
                2900,
            )
        )

        return movies
    }

    fun generateRemoteDummyMovies(): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        movies.add(
            MovieItem(
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                "en",
                "Wonder Woman 1984",
                "Wonder Woman 1984",
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-16",
                464052,
                7.3,
                2255
            )
        )
        movies.add(
            MovieItem(
                "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
                "en",
                "Soul",
                "Soul",
                "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
                "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
                "2020-12-25",
                508442,
                8.4,
                2900,
            )
        )
        return movies
    }
}