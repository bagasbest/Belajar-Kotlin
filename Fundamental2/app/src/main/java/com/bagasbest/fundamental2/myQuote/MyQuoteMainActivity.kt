package com.bagasbest.fundamental2.myQuote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyQuoteMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class MyQuoteMainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MyQuoteMainActivity::class.java.simpleName
    }
    private lateinit var binding: ActivityMyQuoteMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyQuoteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRandomQuote()

        binding.btnAllQuotes.setOnClickListener {
            startActivity(Intent(this, ListQuoteActivity::class.java))
        }

    }

    private fun getRandomQuote () {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "http://quote-api.dicoding.dev/random"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                // Jika koneksi berhasil
                binding.progressBar.visibility = View.INVISIBLE

                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val responseObject = JSONObject(result)

                    val quote = responseObject.getString("en")
                    val author = responseObject.getString("author")

                    binding.tvQuote.text = quote
                    binding.tvAuthor.text = author

                } catch (e: Exception) {
                    Toast.makeText(this@MyQuoteMainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                // Jika koneksi gagal
                binding.progressBar.visibility = View.INVISIBLE

                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MyQuoteMainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}