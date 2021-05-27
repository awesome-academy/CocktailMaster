@file:Suppress("DEPRECATION")

package com.example.cocktailmaster.data.source.remote.utils

import android.os.AsyncTask
import com.example.cocktailmaster.data.model.Drink

const val DRINK_REQUEST_NUMBER = 5

class RemoteRandomDrinkAsynctask(
    private val callback: RequestAPICallback<Drink>,
    private val handle: () -> Drink
) : AsyncTask<Unit, Drink, Unit>() {

    override fun doInBackground(vararg params: Unit?) {
        for (i in 0 until DRINK_REQUEST_NUMBER) {
            publishProgress(handle())
        }
    }

    override fun onProgressUpdate(vararg values: Drink?) {
        super.onProgressUpdate(*values)
        values[0]?.apply(callback::onSuccess)
    }
}
