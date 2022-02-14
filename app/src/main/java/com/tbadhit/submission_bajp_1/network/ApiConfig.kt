package com.tbadhit.submission_bajp_1.network

import com.tbadhit.submission_bajp_1.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service(): ApiService {
        return retrofit().create(ApiService::class.java)
    }
}