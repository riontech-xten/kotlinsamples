package com.xtensolution.sample.mvvm.data.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Vaghela Mithun R. on 30/12/20.
 * vaghela.mithun@gmail.com
 */

class User {
    var id: Int = 0
    var email: String? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null
    var avatar: String? = null

    fun fullName(): String {
        return "$firstName $lastName"
    }
}