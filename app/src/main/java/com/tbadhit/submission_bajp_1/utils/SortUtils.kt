package com.tbadhit.submission_bajp_1.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val NEWEST = "newest"
    const val OLDEST = "oldest"
    const val RATE = "rate"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities where isTvShow = 0 ")
        when(filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY releaseDate ASC")
            }
            RATE -> {
                simpleQuery.append("ORDER BY voteAverage DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities where isTvShow = 1 ")
        when(filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY releaseDate ASC")
            }
            RATE -> {
                simpleQuery.append("ORDER BY voteAverage DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}