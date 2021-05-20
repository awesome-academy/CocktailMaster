package com.example.cocktailmaster.data.source.remote.utils

interface RequestAPICallback<T> {
    fun onSuccess(data: T)
    fun onFailed()
}
