package by.itacademy.korobchits.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadRoundImage(context: Context, url: String, imageView: ImageView, listener: OnLoadImageListener) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(imageView)
}

fun loadImage(context: Context, url: String, imageView: ImageView, listener: OnLoadImageListener) {
    Glide.with(context)
        .load(url)
        .into(imageView)
}

interface OnLoadImageListener {
    fun onOk()
    fun onError()
}