package com.tbadhit.submission_bajp_1.network

import com.tbadhit.submission_bajp_1.BuildConfig
import com.tbadhit.submission_bajp_1.data.source.remote.response.ResponseMovie
import com.tbadhit.submission_bajp_1.data.source.remote.response.ResponseTvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(URL_MOVIE)
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ResponseMovie>

    @GET(URL_TV_SHOW)
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ResponseTvShow>

    companion object {
        const val URL_MOVIE = "discover/movie"
        const val URL_TV_SHOW = "discover/tv"
    }
}