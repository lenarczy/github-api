package com.rale.githubapi.repositories

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryEndPoint {

    @GET("/users/{user}/repos")
    fun getRepository(@Path("user") user: String) : Call<List<RepositoryEntity>>
}