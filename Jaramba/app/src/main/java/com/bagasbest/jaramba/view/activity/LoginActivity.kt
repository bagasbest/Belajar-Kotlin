package com.bagasbest.jaramba.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bagasbest.jaramba.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, BerandaActivity::class.java))
        }

    }


    fun goToRegisterActivity(view: View) {
       startActivity(Intent(view.context, RegisterActivity::class.java))
    }

    fun forgotPassword(view: View) {
        startActivity(Intent(view.context, ForgotPasswordActivity::class.java))
    }

}