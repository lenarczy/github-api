package com.rale.githubapi.user

import com.google.gson.annotations.SerializedName

data class UserEntity(var login: String, var name: String, var followers: String, var following: String, @SerializedName("avatar_url") var avatar: String, var email: String)