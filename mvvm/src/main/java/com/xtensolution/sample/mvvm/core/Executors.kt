package com.xtensolution.sample.mvvm.core


/**
 * Created by Vaghela Mithun R. on 24/12/20.
 * vaghela.mithun@gmail.com
 */

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}