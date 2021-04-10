package com.bagasbest.berepo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.berepo.R
import com.bagasbest.berepo.model.FollowerModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FollowerViewModel : ViewModel(){
    val listFollower = MutableLiveData<ArrayList<FollowerModel>>()

    fun setFollowerUser(username: String) {
        val listItems = ArrayList<FollowerModel>()

        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$username/followers"
        client.addHeader("Authorization", R.string.GITHUB_TOKEN.toString())
        client.addHeader("User-Agent", "request")

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d("RESULT : ", result)

                try {
                    val items = JSONArray(result)
                    Log.d("ITEM RESULT : ", items.length().toString())
                    for(i in 0 until items.length()) {
                        val jsonObject = items.getJSONObject(i)
                        val userItems = FollowerModel()
                        userItems.username = jsonObject.getString("login")
                        userItems.avatar = jsonObject.getString("avatar_url")
                        userItems.id = jsonObject.getInt("id")
                        userItems.url = jsonObject.getString("html_url")
                        listItems.add(userItems)
                    }

                    listFollower.postValue(listItems)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Log.d("onFailure : ", errorMessage)
            }
        })
    }

    fun getFollowerUser() : LiveData<ArrayList<FollowerModel>> {
        return listFollower
    }
}