package com.bagasbest.fundamental2.myBackgroundThread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyBackgroundThreadMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MyBackgroundThreadMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyBackgroundThreadMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBackgroundThreadMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val executor = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.getMainLooper())

        binding.btnStart.setOnClickListener{
            lifecycleScope.launch {
                try {
                    //simulate process compressing
                    for(i in 0 .. 10) {
                        delay(500)
                        val precentage = i * 10
                        withContext(Dispatchers.Main) {
                            // update ui in main thread
                            if(precentage == 100) {
                                binding.tvStatus.setText(R.string.task_completed)
                                binding.hidden.visibility = View.VISIBLE
                            } else {
                                binding.tvStatus.text = String.format(getString(R.string.compressing), precentage)
                            }
                        }

                    }
                }catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

        }

    }
}