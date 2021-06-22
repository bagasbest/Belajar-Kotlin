package com.bagasbest.jaramba.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bagasbest.jaramba.R
import com.bagasbest.jaramba.databinding.ActivityForgotPasswordBinding
import com.bagasbest.jaramba.viewmodel.ForgotPasswordViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private var binding : ActivityForgotPasswordBinding? = null
    private lateinit var forgotPasswordViewModel: ForgotPasswordViewModel
    private var counter: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // click forgot password btn
        binding?.forgotPasswordBtn?.setOnClickListener {
            // form validation
            forgotPasswordFormValidation()
        }
    }

    private fun forgotPasswordFormValidation() {
        val email = binding?.emailEt?.text.toString().trim()

        if(!email.isValidEmail()) {
            binding?.emailEt?.error = resources.getString(R.string.error_email)
            return
        }

        forgotPasswordViewModel = ViewModelProvider(this).get(forgotPasswordViewModel::class.java)

        binding?.progressBar?.visibility = View.VISIBLE
        forgotPasswordViewModel.forgotPassword(email, this)
        forgotPasswordViewModel.getUserMutableLiveData().observe(this, { observer ->
            if(observer != null) {
                binding?.progressBar?.visibility = View.GONE
                forgotPasswordViewModel.getUserMutableLiveData().postValue(null)
                if(counter > 0) {
                    showAlertDialog()
                    counter--
                }
            }
        })

    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.confirmed)
        builder.setMessage(R.string.messageConfirmed)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}