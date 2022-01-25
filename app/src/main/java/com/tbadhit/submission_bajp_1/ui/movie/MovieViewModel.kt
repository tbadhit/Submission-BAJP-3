package com.tbadhit.submission_bajp_1.ui.movie

import androidx.lifecycle.ViewModel
import com.tbadhit.submission_bajp_1.data.ItemEntity
import com.tbadhit.submission_bajp_1.utils.DataMovie

class MovieViewModel : ViewModel() {
    fun getMovies(): List<ItemEntity> = DataMovie.generateDummyMovie()
    fun getMovie(id: Int): ItemEntity = DataMovie.generateDummyMovie()[id]
}