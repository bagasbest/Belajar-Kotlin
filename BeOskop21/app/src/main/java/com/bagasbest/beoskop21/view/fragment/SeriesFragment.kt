package com.bagasbest.beoskop21.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.beoskop21.databinding.FragmentSeriesBinding
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.viewmodel.adapter.SeriesAdapter
import com.bagasbest.beoskop21.viewmodel.viewmodel.SeriesViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory


class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private var tvSeries = listOf<ItemList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null) {
            binding.progressBar.visibility = View.VISIBLE

            val factory = activity?.application?.let {
                ViewModelFactory.getInstance()
            }
            val viewModel = factory?.let { ViewModelProvider(this, it) }?.get(SeriesViewModel::class.java)

            val seriesAdapter = SeriesAdapter()

            binding.progressBar.visibility = View.VISIBLE
            viewModel?.tvSeries()?.observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                tvSeries = it
                seriesAdapter.setData(tvSeries)
            })

            with(binding.rvSeries) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = seriesAdapter
            }
            binding.progressBar.visibility = View.INVISIBLE

        }
    }

}