package com.xtensolution.sample.mvvm.core


/**
 * Created by Vaghela Mithun R. on 03/12/20.
 * vaghela.mithun@gmail.com
 */
sealed class DataResultStatus<T>(
    val data: T? = null,
    val isLoadingShow: Boolean = false,
    val message: String = "",
) {
    class Success<T>(result: T?) : DataResultStatus<T>(data = result)
    class Loading<T>(isLoading: Boolean) : DataResultStatus<T>(isLoadingShow = isLoading)
    class Empty<T>(msg: String) : DataResultStatus<T>(message = msg)
    class Error<T>(msg: String) : DataResultStatus<T>(message = msg)
}