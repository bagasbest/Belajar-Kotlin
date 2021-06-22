package com.bagasbest.jaramba.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bagasbest.jaramba.databinding.FragmentBerandaBinding
import com.bagasbest.jaramba.view.activity.InstantTransportationActivity
import com.bagasbest.jaramba.view.activity.UnderConstructionActivity

class BerandaFragment : Fragment() {

  private var binding: FragmentBerandaBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)
    return binding!!.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding?.travelBusBtn?.setOnClickListener {
      startActivity(Intent(activity, InstantTransportationActivity::class.java))
    }
    binding?.angkotBtn?.setOnClickListener {
      startActivity(Intent(activity, UnderConstructionActivity::class.java))
    }
    binding?.damriBtn?.setOnClickListener {
      startActivity(Intent(activity, UnderConstructionActivity::class.java))
    }
    binding?.rideSharingBtn?.setOnClickListener {
      startActivity(Intent(activity, UnderConstructionActivity::class.java))
    }
    binding?.monorailBtn?.setOnClickListener {
      startActivity(Intent(activity, UnderConstructionActivity::class.java))
    }
    binding?.keretaKrlBtn?.setOnClickListener {
      startActivity(Intent(activity, UnderConstructionActivity::class.java))
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    binding = null
  }
}