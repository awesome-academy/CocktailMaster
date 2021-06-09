@file:Suppress("DEPRECATION")

package com.example.cocktailmaster.data.source.remote.utils

import android.os.AsyncTask

class RemoteAsysntask<T>(
    private val callback: RequestAPICallback<T>,
    private val handle: () -> T?
) : AsyncTask<Unit, Unit, T?>() {
    override fun doInBackground(vararg params: Unit?): T? = handle()

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: callback.onFailed()
    }
}
