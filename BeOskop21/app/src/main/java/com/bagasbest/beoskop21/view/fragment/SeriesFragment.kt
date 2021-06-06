package com.bagasbest.beoskop21.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.beoskop21.databinding.FragmentSeriesBinding
import com.bagasbest.beoskop21.model.source.local.entity.SeriesEntity
import com.bagasbest.beoskop21.model.utils.SortUtils
import com.bagasbest.beoskop21.model.vo.Status
import com.bagasbest.beoskop21.viewmodel.adapter.SeriesAdapter
import com.bagasbest.beoskop21.viewmodel.viewmodel.SeriesViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory


class SeriesFragment : Fragment(), SeriesAdapter.OnItemClickCallBack {

    private lateinit var binding: FragmentSeriesBinding
    private lateinit var viewModel: SeriesViewModel
    private lateinit var seriesAdapter: SeriesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {

            seriesAdapter = SeriesAdapter()
            context?.let {
                viewModel.tvSeries(SortUtils.NEWEST).observe(viewLifecycleOwner, { data ->
                    if(data != null) {
                        when (data.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                seriesAdapter.submitList(data.data)
                                seriesAdapter.setOnItemClickCallBack(this)
                                seriesAdapter.notifyDataSetChanged()
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }

            with(binding.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
        }
    }

    override fun clickFavoriteButton(series: SeriesEntity) {
        Log.d("CheckTitle", series.name.toString())
        Log.d("CekFav", series.isFavorite.toString())

        if(series.isFavorite) {
            viewModel.setFavoriteSeries(series, false)
        } else {
            viewModel.setFavoriteSeries(series, true)
        }
    }

}