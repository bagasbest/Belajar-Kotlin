package com.bagasbest.jaramba.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bagasbest.jaramba.databinding.FragmentBerandaBinding
import com.bagasbest.jaramba.view.activity.InstantTransportationActivity

class BerandaFragment : Fragment() {

  private lateinit var binding: FragmentBerandaBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.travelBusBtn.setOnClickListener {
      startActivity(Intent(activity, InstantTransportationActivity::class.java))
    }
  }
}