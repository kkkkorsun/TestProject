package com.kkkkorsun.testproject.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kkkkorsun.testproject.api.RetrofitClient
import com.kkkkorsun.testproject.data.model.DetailUserResponse
import com.kkkkorsun.testproject.data.model.User
import com.kkkkorsun.testproject.model.RepositoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DetailUserViewModel"

class DetailUserViewModel : ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()
    val repos = MutableLiveData<ArrayList<RepositoryModel>>()

    val tagging = Log.d(TAG, "DetailUserViewModel")

    fun setUserDetail(username: String) {
        Log.d(TAG, "setUserDetail")
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserResponse> {
                override fun onResponse(
                    p0: Call<DetailUserResponse>,
                    response: Response<DetailUserResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body().toString())
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(p0: Call<DetailUserResponse>, p1: Throwable) {
                    Log.d(TAG, "callback user failed")
                }

            })
    }

    fun setUserRepos(username: String) {
        Log.d(TAG, "setUserRepos")
        RetrofitClient.apiInstance
            .getRepos(username)
            .enqueue(object : Callback<ArrayList<RepositoryModel>> {
                override fun onResponse(
                    call: Call<ArrayList<RepositoryModel>>,
                    response: Response<ArrayList<RepositoryModel>>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body().toString())
                        repos.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<RepositoryModel>>, t: Throwable) {
                    Log.d(TAG, "callback repos failed")
                }

            })
    }

    fun getUserDetail(): LiveData<DetailUserResponse>{
        return user
    }

    fun getUserRepos(): MutableLiveData<ArrayList<RepositoryModel>>{
        return repos
    }

}