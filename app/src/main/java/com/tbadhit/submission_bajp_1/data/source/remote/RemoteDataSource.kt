package com.tbadhit.submission_bajp_1.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tbadhit.submission_bajp_1.data.source.remote.response.MovieItem
import com.tbadhit.submission_bajp_1.data.source.remote.response.ResponseMovie
import com.tbadhit.submission_bajp_1.data.source.remote.response.ResponseTvShow
import com.tbadhit.submission_bajp_1.data.source.remote.response.TvShowItem
import com.tbadhit.submission_bajp_1.network.ApiConfig
import com.tbadhit.submission_bajp_1.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovies(): LiveData<ApiResponse<List<MovieItem>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieItem>>>()
        ApiConfig.service().getMovies().enqueue(object : Callback<ResponseMovie> {
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                val result = response.body()?.results
                if (result != null) {
                    resultMovie.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })

        return resultMovie
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvShowItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<TvShowItem>>>()
        ApiConfig.service().getTvShow().enqueue(object : Callback<ResponseTvShow> {
            override fun onResponse(
                call: Call<ResponseTvShow>,
                response: Response<ResponseTvShow>
            ) {
                val result = response.body()?.results
                if (result != null) {
                    resultTvShow.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ResponseTvShow>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }

        })

        return resultTvShow
    }

    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName
    }
}