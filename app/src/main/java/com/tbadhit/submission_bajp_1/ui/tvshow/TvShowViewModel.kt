package com.tbadhit.submission_bajp_1.ui.tvshow

import androidx.lifecycle.ViewModel
import com.tbadhit.submission_bajp_1.data.ItemEntity
import com.tbadhit.submission_bajp_1.utils.DataTvShow

class TvShowViewModel : ViewModel() {
    fun getTvShows(): List<ItemEntity> = DataTvShow.generateDummyTvShow()
    fun getTvShow(id: Int): ItemEntity = DataTvShow.generateDummyTvShow()[id]
}