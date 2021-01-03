package com.xtensolution.sample.mvvm.repository

import com.xtensolution.core.data.JSONDataSource


/**
 * Created by Vaghela Mithun R. on 30/12/20.
 * vaghela.mithun@gmail.com
 */
class UserRepository(private val dataResource: JSONDataSource) {
    fun userData() = dataResource.getUserFromAsset("users")
}