package com.bagasbest.belaundry.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bagasbest.belaundry.R
import com.bagasbest.belaundry.databinding.ActivityAddDataBinding
import com.bagasbest.belaundry.model.api.RetroInstance
import com.bagasbest.belaundry.model.api.RetroService
import com.bagasbest.belaundry.model.model.ResponseModel
import com.bagasbest.belaundry.viewmodel.viewmodel.AddDataViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDataBinding
    //private lateinit var addDataViewModel: AddDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = resources.getString(R.string.add_data_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener{
            checkEmptyField()
        }

    }

    private fun checkEmptyField() {
        val name = binding.etName.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val price = binding.etPrice.text.toString().trim()

        when {
            name.isEmpty() -> {
                binding.etName.error = resources.getString(R.string.fill_name)
                return
            }
            address.isEmpty() -> {
                binding.etAddress.error = resources.getString(R.string.fill_address)
                return
            }
            phone.isEmpty() -> {
                binding.etPhone.error = resources.getString(R.string.fill_phone)
                return
            }
            price.isEmpty() -> {
                binding.etPrice.error = resources.getString(R.string.fill_price)
                return
            }
            else -> saveData(name, address, phone, price)
        }

    }

    private fun saveData(name: String, address: String, phone: String, price: String) {

        showLoading(true)

        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val saveData = retroInstance.createLaundryData(name, address, phone, price)

        saveData.enqueue(object : Callback<ResponseModel>{
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                Toast.makeText(this@AddDataActivity, "Sukses menambah data", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Toast.makeText(this@AddDataActivity, "Gagal menambah data", Toast.LENGTH_SHORT).show()
            }

        })



//        addDataViewModel.addUser(name, address, phone, price)
//        showLoading(false)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }

}