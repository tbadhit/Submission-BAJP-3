package com.tbadhit.submission_bajp_1.ui.tvshow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.databinding.FragmentTvShowBinding
import com.tbadhit.submission_bajp_1.ui.detail.DetailActivity
import com.tbadhit.submission_bajp_1.ui.detail.TypeCatalogue
import com.tbadhit.submission_bajp_1.utils.SortUtils
import com.tbadhit.submission_bajp_1.utils.startActivity
import com.tbadhit.submission_bajp_1.vo.Resource
import com.tbadhit.submission_bajp_1.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding!!

    private val viewModel: TvShowViewModel by viewModel()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowAdapter = TvShowAdapter()
        setList(SortUtils.NEWEST)

        binding.progressBarList.visibility = View.VISIBLE
        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        onClick()
    }

    private fun onClick() {
        binding.apply {
            fabNewest.setOnClickListener { setList(SortUtils.NEWEST) }
            fabOldest.setOnClickListener { setList(SortUtils.OLDEST) }
            fabPopularity.setOnClickListener { setList(SortUtils.RATE) }
        }

        tvShowAdapter.onClick { data ->
            requireContext().startActivity<DetailActivity>(
                DetailActivity.EXTRA_TYPE to TypeCatalogue.TV_SHOW.ordinal,
                DetailActivity.ID_DATA to data.id
            )
        }
    }

    private fun setList(newest: String) {
        viewModel.getTvShows(newest).observe(requireActivity(), tvShowObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val tvShowObserver = Observer<Resource<PagedList<MovieEntity>>> { tvShow ->
        if (tvShow != null) {
            when (tvShow.status) {
                Status.LOADING -> binding.progressBarList.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.progressBarList.visibility = View.GONE
                    tvShowAdapter.apply {
                        submitList(tvShow.data)
                        notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    binding.progressBarList.visibility = View.GONE
                    Toast.makeText(context, R.string.message_error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvShowBinding = null
    }
}