package com.bagasbest.jaramba.view.activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityHistoryDetailBinding
import com.bagasbest.jaramba.model.data.History
import com.bagasbest.jaramba.model.model.HistoryModel
import java.text.SimpleDateFormat
import java.util.*

class HistoryDetailActivity : AppCompatActivity() {

    private var binding: ActivityHistoryDetailBinding? = null
    private var tripId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // SET HISTORY DETAIL VALUE
        setHistoryDetailValue()

        binding?.currentLocationEt?.isEnabled = false
        binding?.destionationLocationEt?.isEnabled = false
        binding?.paymentMethod?.isEnabled = false
        binding?.totalPerson?.isEnabled = false

        // BERI RATING TERHADAP PENGEMUDI
        binding?.giveRating?.setOnClickListener {
            giveRating()
        }

        // KEMBALI KE HALAMAN SEBELUMNYA
        binding?.backButton?.setOnClickListener {
            onBackPressed()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHistoryDetailValue() {
        val history = intent.getParcelableExtra<HistoryModel>(EXTRA_HISTORY) as HistoryModel
        val comment = history.comment
        val currentLocation = history.currentLocation
        val customerUid = history.customerUid
        val destination = history.destination
        val finishDate = history.finishDate
        val paymentMethod = history.paymentMethod
        val rating = history.rating
        val startDate = history.startDate
        val totalPerson = history.totalPerson
        val totalPrice = history.totalPrice
        val transportationMode = history.transportationMode
        val status = history.status
        tripId = history.tripId

        if(status == "Selesai") {
            binding?.giveRating?.visibility = View.GONE
        }

        binding?.transportMode?.text = "Moda Transportasi: $transportationMode"
        binding?.currentLocationEt?.setText(currentLocation)
        binding?.destionationLocationEt?.setText(destination)
        binding?.startDate?.text = "Keberangkatan: $startDate"
        if(finishDate.isNullOrEmpty()) {
            binding?.endDate?.text = "Dalam Perjalanan"
        } else {
            binding?.endDate?.text = "Tiba: $finishDate"
        }

        binding?.totalPerson?.setText(totalPerson.toString())
        binding?.priceTotal?.text = totalPrice.toString()
        binding?.paymentMethod?.setText(paymentMethod)

    }

    @SuppressLint("SimpleDateFormat")
    private fun giveRating() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_rating)
        dialog.setCanceledOnTouchOutside(false)

        val btnSubmit = dialog.findViewById(R.id.submitRating) as Button
        val btnDismiss = dialog.findViewById(R.id.dismissBtn) as Button
        val commentEt = dialog.findViewById(R.id.comment) as EditText
        val ratingBar = dialog.findViewById(R.id.ratingBar) as RatingBar
        val pb = dialog.findViewById(R.id.progress_bar) as ProgressBar

        btnSubmit.setOnClickListener {
            val comment = commentEt.text.toString().trim()
            val rating = ratingBar.rating.toString()

            if(comment.isEmpty()) {
                commentEt.error = "Komentar tidak boleh kosong"
                return@setOnClickListener
            }
            else if(rating == "0.0") {
                Toast.makeText(this, "Rating tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // SIMPAN RATING & COMMENT KE DATABASE
            pb.visibility = View.VISIBLE

            val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy")
            val format = simpleDateFormat.format(Date())

            tripId?.let { it1 ->
                History.setRating(
                    rating,
                    comment,
                    it1,
                    format
                )
            }


            Handler(Looper.getMainLooper()).postDelayed({
                pb.visibility = View.GONE
                if (History.result == true) {
                    Toast.makeText(this, "Berhasil mengakhiri perjalanan", Toast.LENGTH_SHORT).show()
                    binding?.giveRating?.visibility = View.GONE
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, "Gagal mengakhiri perjalanan", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }, 3000)
        }

        btnDismiss.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        const val EXTRA_HISTORY = "history"
    }
}