package com.tbadhit.submission_bajp_1.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getAllTvShow(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataMovie(movies: List<MovieEntity>)

    @Update
    fun updateDataMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1 AND isTvShow = 0")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1 AND isTvShow = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, MovieEntity>

}