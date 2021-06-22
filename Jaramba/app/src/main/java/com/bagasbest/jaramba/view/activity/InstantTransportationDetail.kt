package com.bagasbest.jaramba.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityInstantTransportationDetailBinding
import com.bagasbest.jaramba.model.InstantTransportation
import com.bagasbest.jaramba.view.fragment.DatePickerFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.text.SimpleDateFormat
import java.util.*


class InstantTransportationDetail : AppCompatActivity(), DatePickerFragment.DialogDateListener {

    private var binding: ActivityInstantTransportationDetailBinding? = null
    private var format: String? = null
    private var paymentMethod: String? = null
    private var currentLocation: String? = null
    private var destination: String? = null
    private var priceTotal: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstantTransportationDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // set current location & destination from before layout
        setCurrentLocationAndDestination()

        // set calendar based option choice
        setCalendar()

        // set payment method
        setPaymentMethod()

        // verify all of form
        verifyAllForm()

        // back to previous page
        goBackToPreviousPage()

    }

    private fun goBackToPreviousPage() {
        binding?.backButton?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun verifyAllForm() {
        binding?.letgo?.setOnClickListener {
            when {
                format.isNullOrEmpty() -> {
                    Toast.makeText(this, "Masukkan jadwal keberangkatan", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                binding?.totalPerson?.text.toString().toInt() > 4 -> {
                    binding?.totalPerson?.error = resources.getString(R.string.error_max_person)
                    return@setOnClickListener
                }
                paymentMethod.isNullOrEmpty() -> {
                    Toast.makeText(this, "Pilih metode pembayaran", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            suspend fun saveToDB() =
                coroutineScope {
                    val process = async {
                        binding?.progressBar?.visibility = View.VISIBLE
                        InstantTransportation.saveUserTripToDB(
                            "Travel dan Bus",
                            currentLocation,
                            destination,
                            format,
                            binding?.totalPerson?.text.toString().toInt(),
                            binding?.totalPerson?.text.toString().toInt() * 20000,
                            paymentMethod,
                            FirebaseAuth.getInstance().currentUser?.uid.toString()
                        )
                    }
                    process.await()
                }

            binding?.progressBar?.visibility = View.GONE
            if (InstantTransportation.result == true) {
                Log.e("TAG", "YES")
            }else{
                Log.e("TAG", "NO")
            }
        }
    }

    private fun setPaymentMethod() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.metode_pembayaran,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding?.paymentMethod?.setAdapter(adapter)
        binding?.paymentMethod?.setOnItemClickListener { adapterView, view, i, l ->
            paymentMethod = binding?.paymentMethod?.text.toString()
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setCalendar() {
        val options = intent.getStringExtra(EXTRA_OPTIONS)
        if (options == "one time") {
            val simpleDateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm:ss")
            format = simpleDateFormat.format(Date())

            binding?.goingDate?.text = format
            binding?.goingDate?.isEnabled = false
        } else {
            binding?.goingDate?.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
        }
    }

    private fun setCurrentLocationAndDestination() {
        currentLocation = intent.getStringExtra(EXTRA_CURRENT_LOCATION)
        destination = intent.getStringExtra(EXTRA_DESTINATION)

        binding?.currentLocationEt?.setText(currentLocation)
        binding?.destionationLocationEt?.setText(destination)
        binding?.currentLocationEt?.isEnabled = false
        binding?.destionationLocationEt?.isEnabled = false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val EXTRA_CURRENT_LOCATION = "current_location"
        const val EXTRA_DESTINATION = "destination"
        const val EXTRA_OPTIONS = "options"
        private const val DATE_PICKER_TAG = "DatePicker"
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
        format = simpleDateFormat.format(calendar.time)

        //set untuk TextView
        binding?.goingDate?.text = format
    }
}