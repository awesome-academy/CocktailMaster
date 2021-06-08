package com.example.cocktailmaster.data.source.local.utils

interface OnLocalDataCallback<T> {
    fun onSuccess(data: T)
    fun onFailed()
}
