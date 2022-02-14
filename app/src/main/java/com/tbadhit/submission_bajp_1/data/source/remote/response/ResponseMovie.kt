package com.tbadhit.submission_bajp_1.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseMovie(
	@field:SerializedName("results")
	val results: List<MovieItem>
)

data class MovieItem(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("vote_count")
	val voteCount: Int
)
