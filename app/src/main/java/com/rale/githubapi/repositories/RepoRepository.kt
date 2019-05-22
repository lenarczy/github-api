package com.rale.githubapi.repositories

import android.arch.lifecycle.MutableLiveData
import com.rale.githubapi.base.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    private val apiService = ApiClient.CLIENT.create(RepositoryEndPoint::class.java)

    val getRepos = MutableLiveData<List<RepositoryEntity>>()
    val getError = MutableLiveData<String>()

    fun loadRepositories(user: String) {
        val repository = apiService.getRepository(user)
        repository.enqueue(object : Callback<List<RepositoryEntity>> {
            override fun onFailure(call: Call<List<RepositoryEntity>>?, t: Throwable?) {
                getError.postValue("Error occurred during retrieving repositories ${t?.message}")
            }

            override fun onResponse(call: Call<List<RepositoryEntity>>?, response: Response<List<RepositoryEntity>>?) {
                if (response == null) {
                    getError.postValue("Error occurred during retrieving repositories. Response is null")
                } else {
                    getRepos.postValue(response.body())
                }
            }
        })
    }
}