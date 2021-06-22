package com.bagasbest.jaramba.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityRegisterBinding
import com.bagasbest.jaramba.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private var binding: ActivityRegisterBinding? = null
    private lateinit var registerViewModel: RegisterViewModel
    private var counter: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)



        // click register btn
        binding?.registerBtn?.setOnClickListener {
            // form validation
            registerFormValidation()
        }


    }

    private fun registerFormValidation() {
        val email = binding?.emailEt?.text.toString().trim()
        val password = binding?.passwordEt?.text.toString().trim()
        val username = binding?.usernameEt?.text.toString().trim()
        val confPass = binding?.conformPasswordEt?.text.toString().trim()
        val phone = binding?.phoneEt?.text.toString().trim()

        if(!email.isValidEmail()) {
            binding?.emailEt?.error = resources.getString(R.string.error_email)
            return
        }

        else if(!password.isValidatePassword()) {
            binding?.passwordEt?.error = resources.getString(R.string.error_password)
            return
        }

        else if(!confPass.isValidatePassword()) {
            binding?.conformPasswordEt?.error = resources.getString(R.string.error_password)
            return
        }

        else if(password != confPass) {
            binding?.passwordEt?.error = resources.getString(R.string.password_not_match)
            binding?.conformPasswordEt?.error = resources.getString(R.string.password_not_match)
            return
        }

        else if(username.isEmpty()) {
            binding?.usernameEt?.error = resources.getString(R.string.username_empty)
            return
        }

        else if(phone.isEmpty()) {
            binding?.phoneEt?.error = resources.getString(R.string.empty_phone)
            return
        }

        // observe data when user want to registration
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding?.progressBar?.visibility = View.VISIBLE
        registerViewModel.register(email, password, username, phone, this)
        registerViewModel.getUserMutableLiveData().observe(this, { observer ->
            if(observer != null) {
                binding?.progressBar?.visibility = View.GONE
                registerViewModel.getUserMutableLiveData().postValue(null)
                if(counter > 0) {
                    showAlertDialog()
                    counter--
                }
            }
        })

    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.registered)
        builder.setMessage(R.string.messageRegistered)
        builder.setPositiveButton("YES") { _, _ ->
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }.show()
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}