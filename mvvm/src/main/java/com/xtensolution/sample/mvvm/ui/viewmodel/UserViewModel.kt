package com.xtensolution.sample.mvvm.ui.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtensolution.core.DataResultStatus
import com.xtensolution.core.ioThread
import com.xtensolution.core.data.JSONDataSource
import com.xtensolution.core.data.model.User
import com.xtensolution.sample.mvvm.repository.UserRepository

class UserViewModel(application: Application) : ViewModel() {
    private val repository: UserRepository
    var userData: MutableLiveData<DataResultStatus<List<User>>> = MutableLiveData()

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

    class UserViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}