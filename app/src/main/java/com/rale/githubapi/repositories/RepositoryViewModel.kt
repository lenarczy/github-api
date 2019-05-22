package com.rale.githubapi.repositories

import android.arch.lifecycle.*
import com.rale.githubapi.base.BaseApp

class RepositoryViewModel(application: BaseApp, repository: RepoRepository, login: String) : AndroidViewModel(application) {

    val getRepositories = MediatorLiveData<List<RepositoryModel>>()

    val error: LiveData<String>

    init {
        getRepositories.value = emptyList()
        repository.loadRepositories(login)
        val repositoryEntities = Transformations.map(repository.getRepos, ::fromEntitiesToModels)
        getRepositories.addSource(repositoryEntities, getRepositories::setValue)
        error = repository.getError
    }

    class Factory(private val application: BaseApp, private val login: String): ViewModelProvider.NewInstanceFactory() {

        private val repository = RepoRepository()

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return (RepositoryViewModel(application, repository, login) as T)
        }
    }
}