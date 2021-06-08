@file:Suppress("DEPRECATION")

package com.example.cocktailmaster.data.source.local.utils

import android.os.AsyncTask

class LocalAsynctask<V, T>(
    private val callback: OnLocalDataCallback<T>,
    private val handle: (V) -> T
) : AsyncTask<V, Unit, T?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: V): T? =
        try {
            handle(params[0]) ?: throw Exception("Error load from database")
        } catch (e: Exception) {
            exception = e
            null
        }

    override fun onPostExecute(result: T?) {
        result?.let(callback::onSuccess) ?: callback::onFailed
    }
}