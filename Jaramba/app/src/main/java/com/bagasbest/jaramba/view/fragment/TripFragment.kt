package com.bagasbest.jaramba.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bagasbest.jaramba.databinding.FragmentTripBinding

class TripFragment : Fragment() {

  private var binding: FragmentTripBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentTripBinding.inflate(layoutInflater, container, false)
    return binding!!.root
  }

  override fun onDestroy() {
    super.onDestroy()
    binding = null
  }
}