package com.bagasbest.jaramba.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.FragmentHistoryBinding

class TripFragment : Fragment() {

  private var binding: FragmentHistoryBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
    return binding!!.root
  }

  override fun onDestroy() {
    super.onDestroy()
    binding = null
  }
}