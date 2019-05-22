package com.rale.githubapi.user

import android.arch.lifecycle.*
import com.rale.githubapi.base.BaseApp

class UserViewModel(application: BaseApp, repository: UserRepository, login: String): AndroidViewModel(application) {

    val userModel = MediatorLiveData<UserModel>()
    val error: LiveData<String>

    init {
        userModel.value = fromEntity(UserEntity(login, "", "0", "0", "", ""))
        repository.loadDataFor(login)
        val userModelTmp = Transformations.map(repository.getUser, ::fromEntity)
        userModel.addSource(userModelTmp, userModel::setValue)
        error = repository.getUserError
    }

    class Factory(private val application: BaseApp, private val login: String) : ViewModelProvider.NewInstanceFactory() {

        private val repository = UserRepository()

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return (UserViewModel(application, repository, login) as T)
        }
    }
}