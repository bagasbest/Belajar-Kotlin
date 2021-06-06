package com.bagasbest.beoskop21.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.FragmentSeriesFavoriteBinding
import com.bagasbest.beoskop21.viewmodel.adapter.FavoriteSeriesAdapter
import com.bagasbest.beoskop21.viewmodel.viewmodel.FavoriteViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class SeriesFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentSeriesFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favoriteSeriesAdapter: FavoriteSeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSeriesFavoriteBinding.inflate(layoutInflater, container, false)
        setInitViewModel()
        return binding.root
    }

    private fun setInitViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvSeries)

        favoriteSeriesAdapter = FavoriteSeriesAdapter()
        context?.let {
            viewModel.getFavoriteSeries().observe(viewLifecycleOwner, { data ->
                favoriteSeriesAdapter.submitList(data)
            })
        }

        with(binding.rvSeries) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteSeriesAdapter
        }

    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)


        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val favoriteSeriesEntity = favoriteSeriesAdapter.getSwipedForDelete(swipedPosition)
                favoriteSeriesEntity?.let {
                    viewModel.setFavoriteSeries(it)
                }

                val snackBar = Snackbar.make(requireView(), R.string.deleted, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.clear) { _ ->
                    favoriteSeriesEntity?.let {
                        viewModel.setFavoriteSeries(it)
                    }
                }
                snackBar.show()
            }


        }

    })

}