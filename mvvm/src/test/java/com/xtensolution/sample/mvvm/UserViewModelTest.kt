package com.xtensolution.sample.mvvm

import android.app.Application
import android.database.Observable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.xtensolution.core.DataResultStatus
import com.xtensolution.core.data.model.User
import com.xtensolution.sample.mvvm.repository.UserRepository
import com.xtensolution.sample.mvvm.ui.viewmodel.UserViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: UserRepository

    @Mock
    private lateinit var observable: Observer<DataResultStatus<List<User>>>

    @Before
    fun setup() {
        application = Mockito.mock(Application::class.java)
    }

    @Test
    fun fetchUserTest() {
        `when`(repository.userData()).thenReturn(emptyList<User>())
        val viewModel = UserViewModel(application)
        viewModel.userData.observeForever(observable)
        verify(repository).userData()
        verify(observable).onChanged(DataResultStatus.Success(emptyList()))
        viewModel.userData.removeObserver(observable)
    }
}