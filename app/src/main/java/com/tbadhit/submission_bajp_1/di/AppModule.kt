package com.tbadhit.submission_bajp_1.di
import com.tbadhit.submission_bajp_1.data.source.local.LocalDataSource
import com.tbadhit.submission_bajp_1.data.source.remote.RemoteDataSource
import com.tbadhit.submission_bajp_1.network.ApiConfig
import com.tbadhit.submission_bajp_1.utils.AppExecutors
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.service() }
    single { RemoteDataSource() }
    single { LocalDataSource(get()) }
    single { AppExecutors() }
}