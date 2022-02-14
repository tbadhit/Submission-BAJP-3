package com.tbadhit.submission_bajp_1.di

import androidx.room.Room
import com.tbadhit.submission_bajp_1.data.source.local.room.MovieDatabase
import com.tbadhit.submission_bajp_1.utils.Const.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}