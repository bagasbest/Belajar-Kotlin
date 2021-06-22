package com.bagasbest.jaramba.view.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityInstantTransportationBinding
import com.bagasbest.jaramba.model.InstantTransportation
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class InstantTransportationActivity : FragmentActivity(), OnMapReadyCallback {

    private var binding: ActivityInstantTransportationBinding? = null
    private lateinit var currentLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstantTransportationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()
        // auto request GPS to turn on
        InstantTransportation.showLocationPrompt(this)
        backToBerandaPage()

        //click continue button
        continueButtonClick()
    }

    private fun continueButtonClick() {
        binding?.saveInstantLocationBtn?.setOnClickListener {
            val currentLocation = binding?.currentLocationEt?.text.toString().trim()
            val destination = binding?.destionationLocationEt?.text.toString().trim()

            if(currentLocation.isEmpty()) {
                binding?.currentLocationEt?.error = resources.getString(R.string.error_current_location)
                return@setOnClickListener
            }
            else if(destination.isEmpty()) {
                binding?.destionationLocationEt?.error = resources.getString(R.string.error_destination)
                return@setOnClickListener
            }

            //bring et value to InstantTransportationDetail
            val intent = Intent(this, InstantTransportationDetail::class.java)
            intent.putExtra(InstantTransportationDetail.EXTRA_CURRENT_LOCATION, currentLocation)
            intent.putExtra(InstantTransportationDetail.EXTRA_DESTINATION, destination)
            intent.putExtra(InstantTransportationDetail.EXTRA_OPTIONS, "one time")
            startActivity(intent)
        }
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                permissionCode
            )
            return
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location

                val supportMapFragment =
                    supportFragmentManager.findFragmentById(R.id.google_maps) as SupportMapFragment
                supportMapFragment.getMapAsync(this)
            }
        }
    }

    private fun backToBerandaPage() {
        binding?.backButton?.setOnClickListener {
            val intent = Intent(this, BerandaActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val latLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("I am here!")
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18F))
        googleMap.addMarker(markerOptions)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = true
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] ==
                PackageManager.PERMISSION_GRANTED
            ) {
                fetchLocation()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            LocationRequest.PRIORITY_HIGH_ACCURACY -> {
                if(resultCode == Activity.RESULT_OK) {
                    Log.e("Status", "On")
                } else {
                    Log.e("Status", "Off")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}