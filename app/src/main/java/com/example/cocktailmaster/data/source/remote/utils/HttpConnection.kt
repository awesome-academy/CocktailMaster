package com.example.cocktailmaster.data.source.remote.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun httpRequestAPI(api: String): String {
    val dataResponse = StringBuilder("")
    var urlConnection: HttpURLConnection? = null

    try {
        val url = URL(api)
        urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.connect()
        val bf = BufferedReader(InputStreamReader(urlConnection.inputStream))
        bf.forEachLine { dataResponse.append(it) }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        urlConnection?.disconnect()
    }

    return dataResponse.toString()
}
