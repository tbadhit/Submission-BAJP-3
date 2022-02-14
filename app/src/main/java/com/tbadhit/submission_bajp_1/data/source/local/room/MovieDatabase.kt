package com.tbadhit.submission_bajp_1.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}