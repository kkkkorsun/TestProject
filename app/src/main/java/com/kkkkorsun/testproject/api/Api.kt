package com.kkkkorsun.testproject.api

import com.kkkkorsun.testproject.data.model.DetailUserResponse
import com.kkkkorsun.testproject.data.model.UserResponse
import com.kkkkorsun.testproject.model.RepositoryModel
import retrofit2.Response
import retrofit2.http.*

private const val TOKEN = "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"

interface Api {
    @GET("search/users")
    @Headers("Autorization: token $TOKEN")
    fun getSearchUsers(
        @Query("q") query: String
    ): retrofit2.Call<UserResponse>

    @GET("users/{username}")
    @Headers("Autorization: token $TOKEN")
   fun getUserDetail(
        @Path("username") username: String
    ): retrofit2.Call<DetailUserResponse>

    @GET("users/{username}/repos")
    @Headers("Autorization: token $TOKEN")
    fun getRepos(
        @Path("username") username: String
    ): retrofit2.Call<ArrayList<RepositoryModel>>
}