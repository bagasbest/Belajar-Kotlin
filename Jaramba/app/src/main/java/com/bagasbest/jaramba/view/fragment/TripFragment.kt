package com.bagasbest.jaramba.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.FragmentTripBinding
import com.bagasbest.jaramba.model.data.InstantTransportation
import com.bagasbest.jaramba.model.model.TrayekModel
import com.bumptech.glide.Glide

class TripFragment : Fragment() {

  private var binding: FragmentTripBinding? = null
  private val trayekList = ArrayList<TrayekModel>()
  private var trayekRoute: String? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    binding = FragmentTripBinding.inflate(layoutInflater, container, false)

    //get all trayek & price
    InstantTransportation.getTrayekAndPrice(trayekList)

    binding?.cityTimeIv?.let {
      Glide
        .with(this)
        .load(R.drawable.city_morning)
        .into(it)
    }


    //show trayek dropdown
    Handler(Looper.getMainLooper()).postDelayed({
      showDropdown()
    }, 3000)

    return binding!!.root
  }

  private fun showDropdown() {
    val trayek = ArrayList<String>()
    for (i in 0 until trayekList.size) {
      trayek.add(trayekList[i].trayek!!)
    }


    val adapter: ArrayAdapter<String> = context?.let {
      ArrayAdapter<String>(
        it,
        android.R.layout.simple_spinner_dropdown_item,
        trayek
      )
    }!!
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    // Apply the adapter to the spinner
    binding?.trayekEt?.setAdapter(adapter)
    binding?.trayekEt?.setOnItemClickListener { adapterView, view, i, l ->
      trayekRoute = binding?.trayekEt?.text.toString()
      binding?.price?.text = trayekList[i].price.toString()
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    binding = null
  }
}