package com.example.cocktailmaster.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.cocktailmaster.R

fun ImageView.loadImageByUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.image_error)
        .placeholder(R.drawable.image_error)
        .into(this)
}

fun ImageView.loadImageByResource(res: Int) {
    Glide.with(context)
        .load(res)
        .error(R.drawable.image_error)
        .placeholder(R.drawable.image_error)
        .into(this)
}
