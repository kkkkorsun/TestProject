package com.kkkkorsun.testproject.data.model

data class DetailUserResponse(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val name: String,
    val public_repos: Int
)
