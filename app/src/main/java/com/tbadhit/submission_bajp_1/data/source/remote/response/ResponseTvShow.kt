package com.tbadhit.submission_bajp_1.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseTvShow(

	@field:SerializedName("results")
	val results: List<TvShowItem>,
)

data class TvShowItem(

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("vote_count")
	val voteCount: Int
)

