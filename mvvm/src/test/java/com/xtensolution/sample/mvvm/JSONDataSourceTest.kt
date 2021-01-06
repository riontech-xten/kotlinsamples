package com.xtensolution.sample.mvvm

import android.content.Context
import com.xtensolution.core.data.JSONDataSource
import com.xtensolution.core.data.model.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class JSONDataSourceTest {

    private var dataSource: JSONDataSource? = null

    @Before
    fun setup() {
        val context = mock(Context::class.java)
        dataSource = JSONDataSource(context)
    }

    @Test
    fun testFileExistsInAsset() {
        val value = dataSource!!.fileExists("users")
        Assert.assertEquals(true, value)
    }

    @Test
    fun checkNullValue() {
        val value = dataSource!!.getUserFromAsset("users")
        Assert.assertNotNull(value)
    }

    @Test
    fun testDataFetchSuccess() {
        val value = dataSource!!.getUserFromAsset("users")
        Assert.assertTrue(value?.get(0) is User)
    }
}