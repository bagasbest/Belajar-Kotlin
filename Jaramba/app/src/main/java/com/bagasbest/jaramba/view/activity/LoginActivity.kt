package com.bagasbest.jaramba.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityLoginBinding
import com.bagasbest.jaramba.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private var counter: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // auto login
        autoLogin()

        // click register btn
        binding.loginBtn.setOnClickListener {
            // form validation
            loginFormValidation()
        }

    }

    private fun autoLogin() {
        if(FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, BerandaActivity::class.java))
            finish()
        }
    }

    private fun loginFormValidation() {
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if(!email.isValidEmail()) {
            binding.emailEt.error = resources.getString(R.string.error_email)
            return
        }

        else if(!password.isValidatePassword()) {
            binding.passwordEt.error = resources.getString(R.string.error_password)
            return
        }

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.progressBar.visibility = View.VISIBLE
        loginViewModel.login(email, password, this)
        loginViewModel.getUserMutableLiveData().observe(this, { observer ->
            if(observer != null) {
                binding.progressBar.visibility = View.GONE
                loginViewModel.getUserMutableLiveData().postValue(null)
                if(counter > 0) {
                    startActivity(Intent(this, BerandaActivity::class.java))
                    finish()
                    counter--
                }
            }
        })


    }


    fun goToRegisterActivity(view: View) {
       startActivity(Intent(view.context, RegisterActivity::class.java))
    }

    fun forgotPassword(view: View) {
        startActivity(Intent(view.context, ForgotPasswordActivity::class.java))
    }

    private fun String.isValidEmail() =
        isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isValidatePassword() =
        length >= 6
}