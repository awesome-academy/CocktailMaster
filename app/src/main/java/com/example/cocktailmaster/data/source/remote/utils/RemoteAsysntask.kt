@file:Suppress("DEPRECATION")

package com.example.cocktailmaster.data.source.remote.utils

import android.os.AsyncTask
import com.example.cocktailmaster.utils.AsynctaskState

class RemoteAsysntask<T>(
    private val callback: RequestAPICallback<T>,
    private val handle: () -> T?
) : AsyncTask<Unit, Unit, T?>() {
    override fun doInBackground(vararg params: Unit?): T? {
        if (AsynctaskState.isCancelled) {
            AsynctaskState.isCancelled = false
            cancel(true)
            return null
        }
        return handle()
    }

    override fun onPostExecute(result: T?) {
        AsynctaskState.isCancelled = false
        AsynctaskState.isFinished = true
        result?.let(callback::onSuccess) ?: callback.onFailed()
    }
}
