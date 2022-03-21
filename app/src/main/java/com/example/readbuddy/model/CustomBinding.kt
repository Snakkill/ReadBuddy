package com.example.readbuddy.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.readbuddy.list.GlideApp

    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) =
        GlideApp.
        with(view.context).
        load(url).into(view)
