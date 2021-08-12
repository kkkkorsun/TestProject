package com.kkkkorsun.testproject.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kkkkorsun.testproject.api.RetrofitClient
import com.kkkkorsun.testproject.data.model.User
import com.kkkkorsun.testproject.data.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel : ViewModel() {

    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : retrofit2.Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                   // Log.d("Fail", t.message)
                }

            })
    }
    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }
}