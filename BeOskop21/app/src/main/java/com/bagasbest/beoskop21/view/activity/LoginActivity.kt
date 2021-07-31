package com.bagasbest.beoskop21.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bagasbest.beoskop21.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.login?.setOnClickListener {
            val email = binding?.email?.text.toString().trim()
            val pwd = binding?.password?.text.toString().trim()

            if(email == "bioskop@gmail.com" && pwd == "juli2021") {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Email atau Kata Sandi tidak sesuai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}