package com.bagasbest.berepo.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bagasbest.berepo.model.ResponseUser
import com.bagasbest.berepo.model.UserModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val listUser = MutableLiveData<ArrayList<UserModel>>()
    private val userResponse = MutableLiveData<ArrayList<ResponseUser>>()

    fun setUser(query: String) {
        val listItems = ArrayList<UserModel>()
        val userResponseItem = ArrayList<ResponseUser>()

        val client = AsyncHttpClient()
        val url = "https://api.github.com/search/users?q=$query"
        client.addHeader("Authorization", "token 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")
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
                    val responseObject = JSONObject(result)
                    val totalCount = responseObject.getInt("total_count")
                    val items = responseObject.getJSONArray("items")
                    for(i in 0 until items.length()) {
                        val jsonObject = items.getJSONObject(i)
                        val userItems = UserModel()
                        userItems.username = jsonObject.getString("login")
                        userItems.avatar = jsonObject.getString("avatar_url")
                        userItems.id = jsonObject.getInt("id")
                        listItems.add(userItems)
                    }
                    val userCount = ResponseUser()
                    userCount.totalCount = totalCount
                    userResponseItem.add(userCount)


                    listUser.postValue(listItems)
                    userResponse.postValue(userResponseItem)

                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
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

    fun getUser() : LiveData<ArrayList<UserModel>> {
        return listUser
    }

    fun getUserCount() : LiveData<ArrayList<ResponseUser>> {
        return userResponse
    }

}