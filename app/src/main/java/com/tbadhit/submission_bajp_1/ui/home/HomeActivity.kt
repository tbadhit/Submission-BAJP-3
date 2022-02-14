package com.tbadhit.submission_bajp_1.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.databinding.ActivityHomeBinding
import com.tbadhit.submission_bajp_1.ui.favorite.FavoriteActivity
import com.tbadhit.submission_bajp_1.utils.startActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgFavorite.setOnClickListener {
            startActivity<FavoriteActivity>()
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.tab_1, R.string.tab_2)
    }
}