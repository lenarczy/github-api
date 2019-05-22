package com.rale.githubapi.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL: String = "https://api.github.com/"

object ApiClient {

    val CLIENT: Retrofit by lazy { Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build() }
}