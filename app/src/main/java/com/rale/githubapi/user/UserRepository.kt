package com.rale.githubapi.user

import android.arch.lifecycle.MutableLiveData
import com.rale.githubapi.base.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    private val apiService = ApiClient.CLIENT.create(UserEndPoint::class.java)
    val getUser = MutableLiveData<UserEntity>()
    val getUserError = MutableLiveData<String>()

    fun loadDataFor(user: String) {
        val call = apiService.getUser(user)
        call.enqueue(object : Callback<UserEntity> {
            override fun onFailure(call: Call<UserEntity>?, t: Throwable?) {
                getUserError.postValue("There is a problem with retrieving user ${t?.message}")
            }

            override fun onResponse(call: Call<UserEntity>?, response: Response<UserEntity>?) {
                if (response != null) {
                    getUser.postValue(response.body())
                } else {
                    getUserError.postValue("Retrieved user is null")
                }
            }

        })
    }
}