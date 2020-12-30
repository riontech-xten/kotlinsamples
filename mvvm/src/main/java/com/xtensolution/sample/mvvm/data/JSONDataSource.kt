package com.xtensolution.sample.mvvm.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xtensolution.sample.mvvm.data.model.User
import java.io.IOException


/**
 * Created by Vaghela Mithun R. on 15/12/20.
 * vaghela.mithun@gmail.com
 */
class JSONDataSource(private val context: Context) {

    fun getUserFromAsset(fileName: String): ArrayList<User>? {
        var jsonString: String
        var locations = ArrayList<User>()
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            val listLocationType = object : TypeToken<List<User>>() {}.type
            locations = Gson().fromJson(jsonString, listLocationType)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return locations
    }
}