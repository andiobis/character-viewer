package com.example.characterviewer.ui.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.characterviewer.R

@BindingAdapter("app:imageUrl")
fun loadImage(
    view: ImageView,
    url: String?
) {
    Glide.with(view.context).load(url).placeholder(R.drawable.place_holder).into(view)
}