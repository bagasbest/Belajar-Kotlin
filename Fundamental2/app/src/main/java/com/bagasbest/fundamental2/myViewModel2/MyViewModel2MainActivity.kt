package com.bagasbest.fundamental2.myViewModel2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyViewModel2MainBinding

class MyViewModel2MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyViewModel2MainBinding
   // private lateinit var mainViewModel: MainViewModel
    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyViewModel2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        displayResult()

        binding.btnCalculate.setOnClickListener {
            val width = binding.etWidth.text.toString()
            val height = binding.etHeight.text.toString()
            val length = binding.etLength.text.toString()

            when {
                width.isEmpty() -> {
                    binding.etWidth.error = "Masih kosong"
                }
                height.isEmpty() -> {
                    binding.etHeight.error = "Masih kosong"
                }
                length.isEmpty() -> {
                    binding.etLength.error = "Masih kosong"
                }
                else -> {
                    mainViewModel.calculate(width, height, length)
                    displayResult()
                }
            }
        }


    }

    private fun displayResult() {
        binding.tvResult2.text = mainViewModel.result.toString()
    }
}