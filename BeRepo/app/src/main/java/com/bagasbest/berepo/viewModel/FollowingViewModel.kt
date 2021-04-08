package com.bagasbest.berepo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.berepo.model.FollowingModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class FollowingViewModel : ViewModel(){

    val listFollowing = MutableLiveData<ArrayList<FollowingModel>>()

    fun setFollowingUser(username: String) {
        val listItems = ArrayList<FollowingModel>()

        val client = AsyncHttpClient()
        val url = "https://api.github.com/users/$username/following"
        client.addHeader("Authorization", "token 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")
        client.addHeader("User-Agent", "request")

        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header>,
                responseBody: ByteArray
            ) {
                val result = String(responseBody)
                Log.d("RESULT FOLLOWING: ", result)

                try {
                    val items = JSONArray(result)
                    Log.d("ITEM RESULT : ", items.length().toString())
                    for(i in 0 until items.length()) {
                        val jsonObject = items.getJSONObject(i)
                        val userItems = FollowingModel()
                        userItems.username = jsonObject.getString("login")
                        userItems.avatar = jsonObject.getString("avatar_url")
                        userItems.id = jsonObject.getInt("id")
                        userItems.url = jsonObject.getString("html_url")
                        listItems.add(userItems)
                    }

                    listFollowing.postValue(listItems)

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

    fun getFollowingUser() : LiveData<ArrayList<FollowingModel>>{
        return listFollowing
    }

}