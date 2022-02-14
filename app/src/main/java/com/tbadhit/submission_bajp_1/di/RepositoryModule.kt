package com.tbadhit.submission_bajp_1.di

import com.tbadhit.submission_bajp_1.data.source.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(get(), get(), get()) }
}