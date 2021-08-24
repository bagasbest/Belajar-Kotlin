package com.bagasbest.jaramba.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.FragmentBerandaBinding
import com.bagasbest.jaramba.view.activity.InstantTransportationActivity
import com.bagasbest.jaramba.view.activity.UnderConstructionActivity
import com.bumptech.glide.Glide

class BerandaFragment : Fragment() {

  private var binding: FragmentBerandaBinding? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentBerandaBinding.inflate(layoutInflater, container, false)

    binding?.cityTimeIv?.let {
      Glide
        .with(this)
        .load(R.drawable.city_morning)
        .into(it)
    }

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