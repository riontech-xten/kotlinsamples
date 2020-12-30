package com.xtensolution.sample.mvvm.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtensolution.sample.mvvm.core.DataResultStatus
import com.xtensolution.sample.mvvm.core.ioThread
import com.xtensolution.sample.mvvm.data.JSONDataSource
import com.xtensolution.sample.mvvm.data.model.User
import com.xtensolution.sample.mvvm.repository.UserRepository

class UserViewModel(application: Application) : ViewModel() {
    private val repository: UserRepository
    var userData: MutableLiveData<DataResultStatus<MutableList<User>>> = MutableLiveData()

    init {
        val jsonDataSource = JSONDataSource(application.applicationContext)
        repository = UserRepository(jsonDataSource)
    }

    fun fetchUsers() {
        userData.value = DataResultStatus.Loading(true)
        ioThread {
            try {
                val results = repository.userData()
                if (results == null) {
                    userData.postValue(DataResultStatus.Empty("No data"))
                } else {
                    userData.postValue(DataResultStatus.Success(results))
                }
            } catch (ex: Exception) {
                userData.value = DataResultStatus.Error("Error")
            }
        }
    }

    class UserViewModelFactory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}