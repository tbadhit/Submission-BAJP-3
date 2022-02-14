package com.tbadhit.submission_bajp_1.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tbadhit.submission_bajp_1.ui.favorite.movie.FavoriteMovieFragment
import com.tbadhit.submission_bajp_1.ui.favorite.tvshow.FavoriteTvShowFragment

class FavoriteViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> FavoriteMovieFragment()
        1 -> FavoriteTvShowFragment()
        else -> FavoriteTvShowFragment()
    }
}