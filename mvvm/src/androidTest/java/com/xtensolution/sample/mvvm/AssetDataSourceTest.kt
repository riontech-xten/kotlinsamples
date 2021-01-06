package com.xtensolution.sample.mvvm

import androidx.test.platform.app.InstrumentationRegistry
import com.xtensolution.core.data.JSONDataSource
import com.xtensolution.core.data.model.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AssetDataSourceTest {
    private var dataSource: JSONDataSource? = null

    @Before
    fun setup() {
        val context =  InstrumentationRegistry.getInstrumentation().targetContext
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