package com.tbadhit.submission_bajp_1.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteViewPagerAdapter = FavoriteViewPagerAdapter(this)
        val viewPager = binding.favoriteViewPager

        viewPager.adapter = favoriteViewPagerAdapter

        TabLayoutMediator(binding.tabsFavorite, viewPager) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2)
    }
}