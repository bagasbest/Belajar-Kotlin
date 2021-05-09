package com.bagasbest.jaramba.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityRegisterBinding
import com.bagasbest.jaramba.viewmodel.LoginRegisterViewModel
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loginRegisterViewModel: LoginRegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginRegisterViewModel = ViewModelProvider(this).get(LoginRegisterViewModel::class.java)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()

            if(!email.isValidEmail()) {
                binding.emailEt.error = resources.getString(R.string.error_email)
                return@setOnClickListener
            }

            if(!password.isValidatePassword()) {
                binding.passwordEt.error = resources.getString(R.string.error_password)
                return@setOnClickListener
            }

            loginRegisterViewModel.register(email, password, this)
            loginRegisterViewModel.getUserMutableLiveData().observe(this, { observer ->
                if(observer != null) {
                    Toast.makeText(this, "Pengguna terdaftar", Toast.LENGTH_SHORT).show()
                    loginRegisterViewModel.getUserMutableLiveData().postValue(null)
                }
            })
        }


    }


    fun backToLoginPage(view: View) {
        val intent = Intent(view.context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidatePassword() =
         length >= 6
}