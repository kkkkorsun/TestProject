package com.kkkkorsun.testproject.api

import android.telecom.Call
import com.kkkkorsun.testproject.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Autorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): retrofit2.Call<UserResponse>

}