package com.rale.githubapi.user

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserEndPoint {

    @GET("/users/{user}")
    fun getUser(@Path("user") user: String): Call<UserEntity>
}